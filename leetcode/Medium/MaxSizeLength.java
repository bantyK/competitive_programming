import java.util.*;
import java.util.jar.Attributes;

//1292: https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
public class MaxSizeLength {
    public static void main(String[] args) {
        MaxSizeLength obj = new MaxSizeLength();
        int[][] mat = new int[][]{
                {18, 70},
                {61, 1},
                {25, 85},
                {14, 40},
                {11, 96},
                {97, 96},
                {63, 45}
        };
        System.out.println(obj.maxSideLength(mat, 40184));
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        if (rows == 0) return 0;
        int cols = mat[0].length;
        if (cols == 0) return 0;

        int[][] prefixSum = new int[rows + 1][cols + 1];

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                prefixSum[r][c] = mat[r - 1][c - 1] + prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1];
            }
        }

        for (int[] row : prefixSum) {
            System.out.println(Arrays.toString(row));
        }

        int low = 0;
        int high = Math.min(rows, cols);

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(squareExist(prefixSum, rows, cols, mid, threshold)) {
                // a matrix exist, check if we can maximize this len
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    // Check if there exist a square matrix of size "len" whose sum is less than or equal to threshold
    private boolean squareExist(int[][] sum, int rows, int cols, int len, int threshold) {
        for(int i = len; i <= rows; i++) {
            for(int j = len; j <= cols; j++) {
                int squareSum = sum[i][j] - sum[i - len][j] - sum[i][j - len] + sum[i-len][j-len];
                if(squareSum <= threshold) {
                    return true; // there is atleast one matrix, no need to look further, return.
                }
            }
        }
        return false; // there is not a single matrix whose sum is less than or equal to threshold.
    }
}
