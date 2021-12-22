public class NutAndBolts {
    public static void matchPairs(int[] nuts, int[] bolts, int lo, int hi) {
        
    }
        
    private static void partition(int[] a, int lo, int hi, int pivot) {
        int i = lo; 
        for (int j = lo; j < hi; j++) {
            if (a[j] < pivot) {
                swap(a[j], a[i]);
                i++;
            } else if (a[j] == pivot) {

            }
        }
        return i;
    }

    private static void swap(int n, int m) {
        int temp = n;
        n = m; 
        m = temp;
    }

}

/**
 * https://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
 */
