public class QuickFindUF {
    private int[] ids;

    public QuickFindUF(int n) {
        ids = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return (ids[p] == ids[q]);
    }

    public void union(int p, int q) {
        int n = ids.length;
        int pid = ids[p];
        int qid = ids[q];
        for (int i = 0; i < n; i++) {
            if (i == pid)
                ids[i] = qid;
        }
    }
}