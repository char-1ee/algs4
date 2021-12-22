public class WeightedQuickUnionUF {
    private int[] ids;
    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        ids = new int[N + 1];
        sz = new int[N + 1];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return (root(p) == root(q));
    }

    public int root(int n) {
        while (n != ids[n]) n = ids[n];
        return n;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            ids[i] = j;
            sz[j] += sz[i];            
        } else {
            ids[j] = i;
            sz[i] += sz[j];
        }
    }
}
