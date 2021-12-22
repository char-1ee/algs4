public class QuickUnionCompression {
    private int[] ids;

    public QuickUnionCompression(int n) {
        ids = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
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
        ids[i] = j;
    }
}

