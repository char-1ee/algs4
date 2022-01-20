package wk1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class SocialNetwork {
    // log of timestamps
    private class Log {
        int p;
        int q;
        double time;
    }

    // return the timestamp when all N members connected
    public double findEarliestTime(int n, Log[] times) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        for (int i = 0; i < times.length; i++) {
            uf.union(times[i].p, times[i].q);
            if (uf.count() == 1) return times[i].time;
        }
        return -1;
    }
}