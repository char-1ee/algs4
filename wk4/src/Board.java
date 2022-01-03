import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final int[][] tiles;      // board tiles in 2D array
    private final int size;           // dimension of matrix
    private int blankX; // row of blank tile
    private int blankY; // column of blank tile

    // create a n-by-n board
    public Board(int[][] tiles) {
        this.tiles = copyOf2DArray(tiles);
        this.size = tiles.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] == 0) {
                    this.blankX = i;
                    this.blankY = j;
                    break;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str.append(" ").append(tiles[i][j]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != 0 && perManhattan(i, j, tiles[i][j]) != 0)
                    cnt++;
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != 0)
                    sum += perManhattan(i, j, tiles[i][j]);
            }
        }
        return sum;
    }

    // compute per Manhattan distance
    private int perManhattan(int i, int j, int k) {
        int x = (k - 1) / size;
        int y = (k - 1) % size;
        return Math.abs(i - x) + Math.abs(j - y);
    }

    // is the board the goal board
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (that.size == this.size) && Arrays.deepEquals(that.tiles, this.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> s = new ArrayList<>();
        if (blankX > 0) {
            int[][] copy = copyOf2DArray(this.tiles);
            swap(copy, blankX, blankX - 1, blankY, blankY);
            Board b1 = new Board(copy);
            s.add(b1);
        }
        if (blankX < size - 1) {
            int[][] copy = copyOf2DArray(this.tiles);
            swap(copy, blankX, blankX + 1, blankY, blankY);
            Board b2 = new Board(copy);
            s.add(b2);
        }
        if (blankY > 0) {
            int[][] copy = copyOf2DArray(this.tiles);
            swap(copy, blankX, blankX, blankY, blankY - 1);
            Board b3 = new Board(copy);
            s.add(b3);
        }
        if (blankY < size - 1) {
            int[][] copy = copyOf2DArray(this.tiles);
            swap(copy, blankX, blankX, blankY, blankY + 1);
            Board b4 = new Board(copy);
            s.add(b4);
        }
        return s;
    }

    // copy of nested arrays
    private int[][] copyOf2DArray(int[][] arr) {
        int n = arr.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    // swap two elements in tiles
    private void swap(int[][] arr, int x1, int x2, int y1, int y2) {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copyOf2DArray(tiles);
        if (blankX != 0) swap(copy, 0, 0, 0, 1);
        else swap(copy, 1, 1, 1, 0);
        return new Board(copy);
    }

    // unit testing
    public static void main(String[] args) {
        int[][] t1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] t2 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b1 = new Board(t1);
        Board b2 = new Board(t2);

        StdOut.println("Print out boards: ");
        StdOut.print(b1.toString());
        StdOut.print(b2.toString());
        StdOut.println("Dimension of boards");
        StdOut.println(" 1st dim: " + b1.dimension());
        StdOut.println(" 2nd dim: " + b2.dimension());
        StdOut.println("Hamming and Manhattan distance");
        StdOut.println(" 1st hamming: " + b1.hamming());
        StdOut.println(" 2nd hamming: " + b2.hamming());
        StdOut.println(" 1st manhattan: " + b1.manhattan());
        StdOut.println(" 2nd manhattan: " + b2.manhattan());
        StdOut.println("Is this board the goal board? " + b1.isGoal());
        StdOut.println("Is two boards equal? " + b1.equals(b2));
        StdOut.println("Neighboring boards of b1");
        for (Board b : b1.neighbors()) {
            StdOut.print(b.toString());
        }
        StdOut.println("Print out a twin");
        StdOut.print(b1.twin().toString());
    }
}
