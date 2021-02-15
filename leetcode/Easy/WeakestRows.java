import java.util.*;
//1337 https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
public class WeakestRows {

    public static void main(String[] args) {
        WeakestRows obj = new WeakestRows();

        int[][] matrix = new int[][]{{1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 0}, {1, 1, 0, 0, 0, 0}};
        int[] res = obj.kWeakestRows(matrix, 2);
        System.out.println(res);
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int rows = mat.length;

        List<int[]> weakRows = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            int index = binarySearch(mat[i]);
            if (mat[i][0] == 0) {
                weakRows.add(new int[]{i, 0});
            } else {
                weakRows.add(new int[]{i, index + 1}); // {row, count};
            }
        }

        weakRows.sort((r1, r2) -> {
            if (r1[1] == r2[1]) {
                return r1[0] - r2[0];
            }
            return r1[1] - r2[1];
        });

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = weakRows.get(i)[0];
        }

        return res;
    }

    // returns the last index of 1
    private int binarySearch(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] == 1) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
}
