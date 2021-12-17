import java.util.Arrays;

/**
 * Refer to https://www.baeldung.com/cs/counting-inversions-array
 */
public class CountInversions {

    public int countInvOfFirstType(int[] L, int[] R, int lenL, int lenR) {
        int cnt = 0;        // count of inversions
        int j = 0;          // index of R (right)
        for (int i = 0; i < lenL; i++) {
            while (j < lenR && R[j] < L[i])
                j++;
            cnt += j;
        }
        return cnt;
    }

    public int countInv(int[] A, int lo, int hi) {
        int n = A.length;
        int cnt = 0;        // total count of inversions
        if (n <= 1)
            return 0;

        int lenL = n / 2;
        int lenR = n - lenL;
        int[] L = Arrays.copyOfRange(A, 0, n / 2);
        int[] R = Arrays.copyOfRange(A, n / 2, n);

        int mid = lo + (hi - lo) / 2;
        cnt = countInv(L, lo, mid) + countInv(R, mid + 1, hi);  // recursive calls
        cnt = cnt + countInvOfFirstType(L, R, lenL, lenR);      // recursive operation
        merge(A, lo, hi);                                       // recursive termination

        return cnt;
    }

    private void merge(int[] a, int lo, int hi) {
        int[] aux = new int[a.length];

        for (int k = 0; k <= hi; k++) {
            aux[k] = a[k];
        }
        int mid = lo + (hi - lo) / 2;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (aux[j] < aux[i])
                a[k] = aux[j++];
            else if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else
                a[k] = aux[i++];
        }
    }
}