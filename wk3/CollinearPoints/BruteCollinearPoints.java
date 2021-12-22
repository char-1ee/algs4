import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private Point p, q, r, s; // four points
    private List<LineSegment> lines; // collection of collinear segements

    /**
     * Find all line segments containing 4 points.
     */
    public BruteCollinearPoints(Point[] points) {
        check(points);
        int n = points.length;
        lines = new ArrayList<LineSegment>();
        for (int i = 0; i < n - 3; i++) {
            p = points[i];
            for (int j = i + 1; j < n - 2; j++) {
                q = points[j];
                double pq = p.slopeTo(q);
                for (int k = j + 1; k < n - 1; k++) {
                    r = points[k];
                    double pr = p.slopeTo(r);
                    if (pq == pr) {
                        for (int m = k + 1; m < n; m++) {
                            s = points[m];
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
    public int numberOfSegements() {
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
}