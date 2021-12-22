import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))     // find item on left to swap
                if (i == hi)
                    break;
            while (less(a[lo], a[--j]))     // find item on right to swap
                if (j == lo)
                    break;

            if (i >= j)
                break;                      // check if pointers cross swap
            exch(a, i, j);
        }
        exch(a, lo, j);                     // swap with partitioning item
        return j;           // return index of item now known to be in place
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);               // shuffle to avoid quadaric time
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // if (hi <= lo) return;

        // improvement 1: insertion sort for small subarray
        if (hi < lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }

        // improvement 2: choose median rather than a[lo] for partition
        int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
        swap(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}