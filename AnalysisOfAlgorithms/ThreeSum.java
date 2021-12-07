import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> primSolution(int[] A, int target) { // A buggy solution
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        int n = A.length;
        Arrays.sort(A);

        for (int k = 0; k < n - 2; k++) {
            int i = k + 1;
            int j = n - 1;
            int rest = target - A[k];
            while (i < j) {
                if (A[i] + A[j] == rest) {
                    Collections.addAll(set, A[i], A[j], A[k]);
                    res.add(set);
                    i++;
                    j--;
                } else if (A[i] + A[j] < rest)
                    i++;
                else
                    j++;
            }
        }
        return res;
    }

    public List<List<Integer>> adSolution(int[] A, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int n = A.length;
        Arrays.sort(A);

        for (int k = 0; k < n - 2; k++) {

            // -------------------------------
            if (A[k] > target)
                break;
            // ------------------------------

            int i = k + 1;
            int j = n - 1;
            int rest = target - A[k];
            while (i < j) {
                if (A[i] + A[j] == rest) {
                    List<Integer> set = new ArrayList<>();
                    Collections.addAll(set, A[i], A[j], A[k]);
                    res.add(set);

                    // --------------------------------------------
                    while (i < j && A[i] == A[i + 1])
                        i++;
                    while (i < j && A[j] == A[j - 1])
                        j--;
                    // --------------------------------------------

                    i++;
                    j--;
                } else if (A[i] + A[j] < rest)
                    i++;
                else
                    j++;
            }
        }
        return res;
    }

    public List<List<Integer>> reSolution(int[] A, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(A);
        int n = A.length;

        for (int k = 0; k < n - 2; k++) {
            if (A[k] > target)
                break;
            int sum = target - A[k];
            int l = k + 1;
            int r = n - 2;
            while (l < r) {
                if (A[l] + A[r] == sum) {
                    List<Integer> set = new ArrayList<>();
                    Collections.addAll(set, A[l], A[r], A[k]);
                    res.add(set);

                    while (l < r && A[l] == A[l + 1])
                        l++;
                    while (l < r && A[r] == A[r - 1])
                        r--;
                    l++;
                    r--;
                } else if (A[l] + A[r] < sum) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
