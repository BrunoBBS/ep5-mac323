import java.util.Random;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
    double[] results;
    int n, T;

    public PercolationStats(int n, int trials) {
        results = new double[trials];
        this.n = n;
        T = trials;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            Random rand = new Random();
            while (!perc.percolates()) {
                int row = rand.nextInt();
                int col = rand.nextInt();
                perc.open(row, col);
            }
            results[i] = perc.numberOfOpenSites()/n;
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

