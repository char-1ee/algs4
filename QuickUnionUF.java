public class QuickUnionUF {
    private int[] ids;

    public QuickUnionUF(int n) {
        ids = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return (root(p) == root(q));
    }

    public int root(int m) {
        while (m != ids[m]) m = ids[m];
        return m;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        ids[i] = j;
    }
}
