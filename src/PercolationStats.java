import edu.princeton.cs.algs4.*;
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
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                perc.open(row, col);
            }
            results[i] = (double)perc.numberOfOpenSites()/(double)(n * n);
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

