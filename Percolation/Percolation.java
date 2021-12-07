import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private byte[][] grid;
    private WeightedQuickUnionUF uf;
    private int size; // row number or col number
    private int count; // count of open sites

    public Percolation(int n) {
        if (n <= 0)
            return IllegalArgumentException("n <= 0");

        // Virtual top site: 0, virtual bottom site: n*n + 1
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new byte[n][n];
        this.size = n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0; // 0 stands for blocked, 1 stands for open
            }
        }
    }

    private int convertTo1D(int row, int col) {
        return (row - 1) * size + col;
    }

    public void open(int row, int col) {

        // if site(row, col) already opened
        if (isOpen(row, col))
            return;

        // Open the site
        grid[row - 1][col - 1] = 1;
        count++;

        // Change the 2D index into 1D index for union find
        int index = convertTo1D(row, col);

        // Connect top row and bottom row open sites
        if (row == 1) {
            uf.union(index, 0);
        } else if (row == size) {
            uf.union(index, size * size + 1);
        } else {

            // Connect adjacent open sites
            int adjIndex;
            if (isOpen(row + 1, col)) {
                adjIndex = convertTo1D(row + 1, col);
                uf.union(adjIndex, index);
            }
            if (isOpen(row - 1, col)) {
                adjIndex = convertTo1D(row - 1, col);
                uf.union(adjIndex, index);
            }
            if (isOpen(row, col + 1)) {
                adjIndex = convertTo1D(row, col + 1);
                uf.union(adjIndex, index);
            }
            if (isOpen(row, col - 1)) {
                adjIndex = convertTo1D(row, col - 1);
                uf.union(adjIndex, index);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        checkInBounds(row, col);
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        checkInBounds(row, col);
        int index = convertTo1D(row, col);
        return uf.find(index) == 0;
    }

    public int numOfOpenSites() {
        return count;
    }

    public boolean perlocates() {
        return uf.connected(0, size * size + 1);
    }

    private void checkInBounds(int row, int col) {
        if (row < 1 || col > size) {
            throw new IllegalArgumentException("Row or col exceed prescribed bounds.");
        }
    }
    public static void main(String[] args) {
        
    }
}
