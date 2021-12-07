import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;

    private double[] threshold; // Fraction of opened sites
    private final int trials; // Trails times
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must larger than 0.");
        }

        this.trials = trials;
        threshold = new double[trials]; 

        for (int k = 0; k < trials; k++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;
                p.open(i, j);
            }
            threshold[k] = (double) (p.numberOfOpenSites() / n * n);
        }

        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stddev;
    }

    public double confidenceHi() {
        double hi = mean - CONFIDENCE_95 * stddev / Math.sqrt(trials);
        return hi;
    }

    public double confidenceLo() {
        double lo = mean + CONFIDENCE_95 * stddev / Math.sqrt(trials);
        return lo;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trails);
        StdOut.println("mean                     =" + ps.mean());
        StdOut.println("stddev                   =" + ps.stddev());
        StdOut.println("95% confidence interval  = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
