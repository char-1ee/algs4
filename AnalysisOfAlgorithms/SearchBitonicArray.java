public class BitonicArraySearch {
    public boolean solution(int[] A, int tgt) {
        int n = A.length;
        int peak;
        for (int i = 0;; i++) {
            if (A[i] < A[i + 1]) {
                peak = i;
                break;
            }
        }

        if (A[peak] < tgt)
            return false;
        else if (A[peak] == tgt)
            return true;
        else {
            return binarySearch(A, 0, peak - 1, tgt) || binarySearch(A, peak + 1, n - 1, tgt);
        }
        return false;
    }

    public boolean binarySearch(int[] arr, int i, int j, int tgt) {
        while (i < j) {
            int mid = i + (i - j) >> 2;
            if (arr[mid] < tgt)
                j = mid - 1;
            else if (arr[mid] > tgt)
                i = mid + 1;
            else
                return true;
        }
        return false;
    }
}
