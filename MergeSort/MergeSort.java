import edu.princeton.cs.algs4.Insertion;

public class MergeSort {
    private static final int CUTOFF = 7;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);        // precondition: a[lo..mid] sorted
        assert isSorted(a, mid + 1, hi);    // precondition: a[mid+1..hi] sorted

        for (int k = lo; k <= hi; k++)      // copy to auxiliary array
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)      // merge
        {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);         // postcondition: a[lo..hi] sorted
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {        // improvement 1
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!less(a[mid + 1], a[mid]))      // improvement 2
            return;
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(Comparable[] a) {
        Comparable[] aux = (Comparable[])new Object[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    // Temp methods
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static boolean isSorted(Comparable[] a, int i, int hi) {
        return false;
    }
}
