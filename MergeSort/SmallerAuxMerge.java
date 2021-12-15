import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class SmallerAuxMerge {

    public static void main(String[] args) {
        int n = 10;
        int[] subarr1 = new int[n];
        int[] subarr2 = new int[n];
        int[] arr = new int[2 * n];

        for (int i = 0; i < n; i++) {
            subarr1[i] = StdRandom.uniform(100);
            subarr2[i] = StdRandom.uniform(100);
        }
        Arrays.sort(subarr1);
        Arrays.sort(subarr2);

        for (int i = 0; i < n; i++) {
            arr[i] = subarr1[i];
            arr[n + i] = subarr2[i];
        }
        System.out.println("Before merging: " + Arrays.toString(arr));
        merge(arr);
        System.out.println("After merging: " + Arrays.toString(arr));
    }

    private void merge(Comparable[] arr) {
        int n = arr.length;
        int l = 0, r = n;
        Comparable[] aux = new Comparable[n];
        for (int i = 0; i < n; i++) {
            aux[i] = arr[i];
        }

        for (int i = 0; i < 2 * n; i++) {
            if (l >= n)
                break;
            else if (r >= 2 * n)
                arr[i] = aux[l++];
            else if (less(arr[r], arr[l]))
                arr[i] = arr[r++];
            else if (less(arr[l], arr[r]))
                arr[k] = aux[l++];
        }

    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
