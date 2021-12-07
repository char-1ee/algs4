import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private double[] threshold; // Fraction of opened sites
    private int trials; // Trails times
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must larger than 0.");
        }

        this.trials = trials;

        for (int k = 0; k < trials; k++) {
            Percolation p = new Percolation(n);
            while (!p.perlocates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;
                p.open(i, j);
            }
            threshold[k] = (double) (p.numOfOpenSites() / n * n);
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
        double hi = mean - 1.96 * sqrt(stddev) / sqrt(trials);
    return hi;    }

    public double confidenceLo() {
        double lo = mean + 1.96 * sqrt(stddev) / sqrt(trials);
        return lo;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(args[0], args[1]);
        StdOut.println("mean                     =" + ps.mean());
        StdOut.println("stddev                   =" + ps.stddev());
        StdOut.println("95% confidence interval  = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
