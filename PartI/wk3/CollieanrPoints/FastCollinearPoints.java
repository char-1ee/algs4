import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> lines; // collection of collinear segments

    /**
     * Finds all lines segment containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {

        // check for all corner cases
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicates(sortedPoints);

        lines = new LinkedList<>();
        int n = points.length;  // number of points
        for (int i = 0; i < n - 3; i++) {
            Point origin = sortedPoints[i]; // the origin point

            // sort the sub-array except origin point by slope
            Point[] sortedPointsBySlope = sortedPoints.clone();
            Arrays.sort(sortedPointsBySlope, origin.slopeOrder());  // origin is at arr[0]

            double slope = Double.NEGATIVE_INFINITY;    // initialize slope
            List<Point> cand = new LinkedList<>(); // candidate list

            for (int j = 1; j < n; j++) {
                Point p = sortedPointsBySlope[j];

                // if slope of next adjacent point equals
                if (slope == origin.slopeTo(p)) {
                    cand.add(p);
                } else {
                    if (cand.size() >= 3 && origin.compareTo(Collections.min(cand)) <= 0)
                        lines.add(new LineSegment(origin, Collections.max(cand)));
                    cand = new LinkedList<>(Collections.singletonList(p));
                    slope = origin.slopeTo(p);
                }
            }
            if (cand.size() >= 3 && origin.compareTo(Collections.min(cand)) <= 0)
                lines.add(new LineSegment(origin, Collections.max(cand)));
        }
    }

    /**
     * Return the number of line segments
     */
    public int numberOfSegments() {
        return lines.size();
    }

    /**
     * Return the line segments.
     */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[lines.size()];
        lines.toArray(lineSegments);
        return lineSegments;
    }

    /**
     * Check corner cases.
     */
    private void checkNull(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("The array is null.");
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("The array contains null element.");
        }
    }

    /**
     * Check for duplicates.
     */
    private void checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Find duplicates in array.");
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
