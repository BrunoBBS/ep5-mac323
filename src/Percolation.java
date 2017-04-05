// This class provides methods for reading strings and numbers from standard
// input, file input, URLs, and sockets.
// https://www.ime.usp.br/~pf/sedgewick-wayne/stdlib/documentation/index.html
import edu.princeton.cs.algs4.In;

// This class provides methods for printing strings and numbers to standard output.
// https://www.ime.usp.br/~pf/sedgewick-wayne/stdlib/documentation/index.html
// http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdOut.html
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;

public class Percolation {

    // Another union-find for the backwash
    WeightedQuickUnionUF uf, bw;
    private int[][] board;
    private int size;
    private int nOpen;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("The board size must be > 0");
        uf = new WeightedQuickUnionUF((n * n) + 2);
        bw = new WeightedQuickUnionUF((n * n) + 1);
        board = new int[n][n];
        size = n;
        nOpen = 0;
        for (int i = 0; i < size; i++) {
            bw.union(i, size * size);
            uf.union(i, size * size);
            uf.union(((size - 1) * size) + i, (size * size) + 1);
        }
    }

    // Opens the site if its not already open
    public void open(int row, int col) {
        //corner cases
        if (!(row < size && col < size && row >= 0 && col >= 0)) throw new IndexOutOfBoundsException("The site must be inside the board.");

        if (board[row][col] < 1) {
            board[row][col] = 1;
            nOpen++;
            // Check neighbors
            // Top
            if (row > 0 && board[row - 1][col] == 1) {
                uf.union((row * size) + col , ((row - 1) * size) + col);
                bw.union((row * size) + col , ((row - 1) * size) + col);
            }
            // Left
            if (col > 0 &&board[row][col - 1] == 1) {
                uf.union((row * size) + col , (row * size) + col - 1);
                bw.union((row * size) + col , (row * size) + col - 1);
            }
            // Bottom
            if (row < size - 1 && board[row + 1][col] == 1) {
                uf.union((row * size) + col , ((row + 1) * size) + col);
                bw.union((row * size) + col , ((row + 1) * size) + col);
            }
            // Right
            if (col < size - 1 && board[row][col + 1] == 1) {
                uf.union((row * size) + col , (row * size) + col + 1);
                bw.union((row * size) + col , (row * size) + col + 1);
            }
        }
    }

    // Resturs if the site is open
    public boolean isOpen(int row, int col) {
        if (!(row < size && col < size)) throw new IndexOutOfBoundsException("The site must be inside the board.");
        return board[row][col] == 1;
    }

    // Returns if the site is full(connected with top)
    public boolean isFull(int row, int col) {
        if (!(row < size && col < size)) throw new IndexOutOfBoundsException("The site must be inside the board.");
        return bw.connected((row * size) + col, size * size) && board[row][col] == 1;
    }

    public int numberOfOpenSites() {
        return nOpen;
    }

    // Returns if the board percolates
    public boolean percolates() {
        return uf.connected((size * size), (size * size) + 1);
    }

    // Unit test
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Use: java Percolation <grid_size> <number_of_tests>");
            return;
        }
        PercolationStats experiment = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(experiment.mean());
        System.out.println(experiment.stddev());
        System.out.println(experiment.confidenceLow());
        System.out.println(experiment.confidenceHigh());
    }
}
