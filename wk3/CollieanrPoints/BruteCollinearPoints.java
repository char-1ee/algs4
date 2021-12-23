import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> lines; // collection of collinear segments

    /**
     * Find all line segments containing 4 points.
     */
    public BruteCollinearPoints(Point[] points) {

        // check for all corner cases
        checkNull(points);
        Point[] sortedArray = points.clone();
        Arrays.sort(sortedArray);
        checkDuplicates(sortedArray);

        int n = points.length;
        lines = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            Point p = sortedArray[i];
            for (int j = i + 1; j < n - 2; j++) {
                Point q = sortedArray[j];
                double pq = p.slopeTo(q);
                for (int k = j + 1; k < n - 1; k++) {
                    Point r = sortedArray[k];
                    double pr = p.slopeTo(r);
                    if (pq == pr) {
                        for (int m = k + 1; m < n; m++) {
                            Point s = sortedArray[m];
                            double ps = p.slopeTo(s);
                            if (pr == ps) {
                                lines.add(new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }
    }

    /** Return the number of line segments. */
    public int numberOfSegments() {
        return lines.size();
    }

    /** Return the line segments. */
    public LineSegment[] segments() {

        // must allocate space before toArray() reference types
        LineSegment[] lineSegments = new LineSegment[lines.size()];
        lines.toArray(lineSegments); // fill the array
        return lineSegments;
    }

    /** Check corner cases. */
    private void checkNull(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("The array is null.");
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("The array contains null element.");
        }
    }

    /** Check for duplicates. */
    private void checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Find duplicates in array.");
        }
    }
}