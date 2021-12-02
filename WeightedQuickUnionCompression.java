public class WeightedQuickUnionCompression {
    private int[] ids;
    private int[] sz;

    public WeightedQuickUnionCompression(int N) {
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

    public int root(int m) {
        int root = m;
        while (root != ids[root]) root = ids[root];
        while (m != root) {
            
            // last: the last parent node of m who shares a same root with m, 
            // the iteration goes on last after m links to root. 
            int last = ids[m];          
            ids[m] = root;
            m = last;
        }
        return m;
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
