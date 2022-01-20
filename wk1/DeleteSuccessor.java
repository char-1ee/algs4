package wk1;

/** Choose union-find to ensure logarithmic time */
public class DeleteSuccessor {
    private int[] id;
    private int[] largest;
    private int[] size;

    public DeleteSuccessor(int N) {
        this.id = new int[N];
        this.largest = new int[N];
        this.size = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            largest[i] = i;
            size[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return; 
        else if (size[i] > size[j]) { // weighted
            id[i] = j;
            size[j] += size[i];
            if (largest[i] > largest[j]) 
                largest[j] = largest[i];
            else largest[i] = largest[j];
        } else {
            id[j] = i;
            size[i] += size[j];
            if (largest[i] < largest[j]) 
                largest[i] = largest[j];
            else largest[j] = largest[i];
        }
    }

    // remove x from set, return smallest y in set that y >= x
    // https://github.com/phareskrad/algs4/blob/master/jobinterviewquestions/UnionFind.java
    public int delete(int x) {
        int rooty = root(x + 1);
        if (size[rooty] < size[x]) {
            id[rooty] = id[x];
            size[x] += size[rooty];
            largest[x] = largest[rooty];
        } else {
            id[x] = id[rooty];
            size[rooty] += size[x];
        }
        return x + 1;
    }

    // find the largest element in connect component of i
    public int find(int i) {
        int root = root(i);
        return largest[root];
        // return largest[i];
    }
}
