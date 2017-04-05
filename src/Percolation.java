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

    WeightedQuickUnionUF uf;
    int[][] board;
    int size;

    public Percolation(int n) {
        if (n <= 0) throw IllegalArgumentException("The board size must be >= 0");
        uf = new WeightedQuickUnionUF(n * n);
        board = new int[n][n];
        size = n;
    }

    // Opens the site if its not already open
    public void open(int row, int col) {
        //corner cases
        if (!(row < size && col < size)) throw IndexOutOfBoundsException("The site must be inside the board.");

        if (board[row][col] < 1) {
            board[row][col] = 1;
            // check neighbors
            // top
            if (board[row - 1][col] == 1) {
                uf.union((row * size) + col , ((row - 1) * size) + col);
                if (board[row - 1][col] == 2)
                    board[row][col] = 2;
            }
            // left
            if (board[row][col - 1] == 1) {
                uf.union((row * size) + col , (row * size) + col - 1);
            }
            // bottom
            if (board[row + 1][col] == 1) {
                uf.union((row * size) + col , ((row + 1) * size) + col);
            }
            // right
            if (board[row][col + 1] == 1) {
                uf.union((row * size) + col , (row * size) + col + 1);
            }
        }
    }

    // Resturs if the site is open
    public boolean isOpen(int row, int col) {
        if (!(row < size && col < size)) throw IndexOutOfBoundsException("The site must be inside the board.");
        return board[row][col] == 1;
    }

    // Returns if the site is full(connected with top)
    public boolean isFull(int row, int col) {
        if (!(row < size && col < size)) throw IndexOutOfBoundsException("The site must be inside the board.");
        return board[row][col] == 2;
    }

    public int numberOfOpenSites() {
        // Pensar num jeito de fazer isso O(1)
    }

    // Returns if the board percolates
    public boolean percolates() {

    }

    // Unit test
    public static void main(String[] args) {

    }
}
