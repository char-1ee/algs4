import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final Point[] ps;
    private final List<LineSegment> lines; // collection of collinear segments

    /** Finds all lines segment containing 4 or more pointss */
    public FastCollinearPoints(Point[] points) {
        this.ps = points;
        check(points);
        int n = ps.length;
        lines = new ArrayList<LineSegment>();
        Point[] sortedPoints = ps.clone();  // sorted ps array
        Arrays.sort(sortedPoints);              // which is repeated done in check()

        for (int i = 0; i < n - 3; i++) {

            // the origin point for comparisons
            Point p0 = sortedPoints[i];
            Point[] sortedPointsBySlope = sortedPoints.clone();
            Arrays.sort(sortedPointsBySlope, p0.slopeOrder());
            List<Point> cand = new ArrayList<Point>();  // collection for candidate ps
            double slope = Double.NEGATIVE_INFINITY;    // initial slope

            // j starts from 1 since sortedPoint[i] is at sortedPointBySlope[0]
            for (int j = 1; j < n; j++) {
                Point p1 = sortedPointsBySlope[j];
                if (p0.slopeTo(p1) == slope) {
                    cand.add(p1);
                } else {
                    if (cand.size() > 2) lines.add(new LineSegment(p0, p1));
                    cand = new ArrayList<>();
                    slope = p0.slopeTo(p1);
                } 
                if (cand.size() > 2) lines.add(new LineSegment(p0, p1));    // the last pair of collinear ps 
            }
        }
    }

    /** Return the number of line segments */
    public int numberOfSegments() {
        return lines.size();
    }

    /** Return the line segments. */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[lines.size()];
        lines.toArray(lineSegments);
        return lineSegments;
    }

    /** Check corner cases. */
    private void check(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Given null array");
        Point[] copy = points.clone();
        Arrays.sort(copy);
        int i = 1;
        for (Point point : copy) {
            if (point == null)
                throw new IllegalArgumentException("Find null elements");
            if (i > 1 && point.compareTo(points[i - 1]) == 0)
                throw new IllegalArgumentException("Find duplicates");
            i++;
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
