import edu.princeton.cs.algs4.*;
import java.lang.Math;

public class PercolationStats {
    double[] results;
    int n, T;

    public PercolationStats(int n, int trials) {
        results = new double[trials];
        this.n = n;
        T = trials;
        //StdDraw.enableDoubleBuffering();
        int max_threads = 16;

        PercolationThread[] threads = new PercolationThread[trials];
        try {
            for (int i = 0; i < trials; i++) {
                if (i > max_threads) threads[i - max_threads].join();
                threads[i] = new PercolationThread(i);
                threads[i].start();
            }

            for (int i = 0; i < trials; i++)
                threads[i].join();
        } catch (Exception exception) {
        }



        for (int i = 0; i < trials; i++) {
            System.out.println("results[" + i + "]: " + results[i]);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }


    public double confidenceLow() {
        return (mean() - (1.96 * stddev())/Math.sqrt(T));
    }

    public double confidenceHigh() {
        return (mean() + (1.96 * stddev())/Math.sqrt(T));
    }

    public class PercolationThread extends Thread {
        private int thread_id;

        PercolationThread(int thread_id) {
            this.thread_id = thread_id;
        }

        public void run() {
            System.out.println("Iteração numero: " + thread_id);
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                perc.open(row, col);
                //System.out.println("Abriu: " + row + " " + col);
                //PercolationVisualizer.draw(perc, n);
                //StdDraw.show();
            }
            results[thread_id] = (double)perc.numberOfOpenSites()/(double)(n * n);
            //StdDraw.pause(500);
        }
    }
}

