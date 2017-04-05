import edu.princeton.cs.algs4.*;
import java.lang.Math;

public class PercolationStats {
    double[] results;
    int n, T;

    public PercolationStats(int n, int trials) {
        results = new double[trials];
        this.n = n;
        T = trials;
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < trials; i++) {
            System.out.println("Iteração numero: " + i);
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                perc.open(row, col);
                //System.out.println("Abriu: " + row + " " + col);
                PercolationVisualizer.draw(perc, n);
                StdDraw.show();
            }
            results[i] = (double)perc.numberOfOpenSites()/(double)(n * n);
            System.out.println("results[" + i + "]: " + results[i]);
            StdDraw.pause(500);
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
}

