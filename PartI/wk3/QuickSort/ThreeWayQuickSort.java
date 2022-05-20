public class ThreeWayQuickSort {
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)   return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;

        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}

/**
 * Let v be partitioning item a[lo].
 * Scan i from left to right.
 * – (a[i] < v): exchange a[lt] with a[i]; increment both lt and i
 * – (a[i] > v): exchange a[gt] with a[i]; decrement gt
 * – (a[i] == v): increment i
 */
