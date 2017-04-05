

public class PercolationStats {
    double[] results;
    int n;
    Percolation perc;

    public PercolationStats(int n, int trials) {
        results = new double[trials];
        this.n = n;
        perc = new Percolation(n);
    }

    public double mean() {

    }

    public double stddev() {

    }


    public double confidenceLow() {

    }

    public double confidenceHigh() {

    }
}

