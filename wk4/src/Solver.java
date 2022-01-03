import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private List<Board> path;  // solution path
    private final boolean solvable;   // flag of isSolvable()
    private int minMoves;  // number of moves to reach goal board

    // search node as an inner static class
    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node prev;
        private final int moves;
        private final int priority;

        Node(Board board, Node prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
        }

        public Board getBoard() {
            return board;
        }

        public Node getPrev() {
            return prev;
        }

        public int getMoves() {
            return moves;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(Node that) {
            return this.getPriority() - that.getPriority();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("Initial board cannot be null.");

        MinPQ<Node> pq = new MinPQ<>();
        MinPQ<Node> twin = new MinPQ<>();
        pq.insert(new Node(initial, null, 0));
        twin.insert(new Node(initial.twin(), null, 0));

        while (true) {
            Node curr = pq.delMin();
            Node currOfTwin = twin.delMin();

            if (curr.getBoard().isGoal()) {
                solvable = true;
                minMoves = curr.getMoves();
                path = findPath(curr);
                break;
            }
            if (currOfTwin.getBoard().isGoal()) {
                solvable = false;
                break;
            }

            int move = curr.getMoves();

            for (Board b : curr.getBoard().neighbors()) {
                if (curr.getPrev() == null || !b.equals(curr.getPrev().getBoard()))
                    pq.insert(new Node(b, curr, move + 1));
            }
            for (Board b : currOfTwin.getBoard().neighbors()) {
                if (currOfTwin.getPrev() == null || !b.equals(currOfTwin.getPrev().getBoard()))
                    twin.insert(new Node(b, currOfTwin, move + 1));
            }
        }
    }

    // find the shortest path by goal board in tree
    private List<Board> findPath(Node node) {
        List<Board> s = new ArrayList<>();
        Node curr = node;

        while (curr != null) {
            s.add(curr.getBoard());
            curr = curr.getPrev();
        }
        Collections.reverse(s);
        return s;
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? minMoves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return path;
    }

    // test client
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
