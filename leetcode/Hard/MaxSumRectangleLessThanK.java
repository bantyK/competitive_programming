//363 https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/

import java.util.TreeSet;

public class MaxSumRectangleLessThanK {
    public static void main(String[] args) {
        MaxSumRectangleLessThanK obj = new MaxSumRectangleLessThanK();
        int[][] matrix = new int[][]{
                {-24, 10, 7, 18, -13, 20, -1},
                {10, 12, -21, -12, -16, 1, 11},
                {-25, 5, -8, 4, -23, 12, 5},
                {-18, -4, 15, -22, 11, -1, -3}
        };
//        System.out.println(obj.maxSumSubmatrix(matrix, 25));

        int[][] matrix1 = new int[][]{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}};
//        System.out.println(obj.maxSumSubmatrix(matrix1, 10));

        int[][] matrix2 = new int[][]{{2, 2, -1}};
//        System.out.println(obj.maxSumSubmatrix(matrix2, 0));

        int[][] matrix3 = new int[][]{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}};
        System.out.println(obj.maxSumSubmatrix(matrix3, 8) == 8);
    }

    public int maxSumSubmatrix1(int[][] matrix, int k) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0)
            return 0;

        int res = Integer.MIN_VALUE;

        for (int L = 0; L < cols; L++) {
            int[] rowSum = new int[rows];
            for (int R = L; R < cols; R++) {

                for (int r = 0; r < rows; r++) {
                    rowSum[r] += matrix[r][R];
                }

                int max = rowSum[0];
                int curr = 0;

                // Kadane's algorithm (without the extra space version)
                for (int num : rowSum) {
                    curr = Math.max(num, num + curr);
                    max = Math.max(curr, max);
                    if (max == k) {
                        // answer cannot be greater than k
                        // if we have found the sum == k, then no need to go continue, thats the maximum we can get
                        return max;
                    }
                }


                if (max < k) {
                    res = Math.max(res, max);
                } else {
                    // max is greater than k
                    // Find the sub-array which has greatest sum less than equal to K
                    for (int i = 0; i < rows; i++) {
                        int currSum = 0;
                        for (int j = i; j < rows; j++) {
                            currSum += rowSum[j];
                            if (currSum <= k) {
                                res = Math.max(res, currSum);
                            }
                        }
                    }
                    if (res == k) return res;
                }
            }
        }

        return res;
    }

    // Solution using tree set //
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int res = Integer.MIN_VALUE;

        for (int L = 0; L < cols; L++) {
            int[] rowSum = new int[rows];

            for (int R = L; R < cols; R++) {

                for (int r = 0; r < rows; r++) {
                    rowSum[r] += matrix[r][R];
                }

                res = Math.max(res, findMaxSum(rowSum, k));
            }
        }
        return res;
    }


    // These are the helper functions which explain the concepts used to solve the problem
    // This is a helper function which returns the largest sum less than equal to K

    /**
     * The subarray sum is sums[j] - sums[i]
     * we need to maximize this.
     * In order to maximize this value we need to minimize sums[i].
     * But what is the minimum value of sums[i] that we can use ??
     * In order to calculate this value, lets re-arrange this equation
     * <p>
     * sums[j] - sums[i] <= k
     * -sums[i] <= k - sums[j]
     * sums[i] >= sums[j] - k
     * <p>
     * So the we need to minimize sums[i], but is has to greater than sums[j] - k
     * <p>
     * Hence we are looking for the MINIMUM value of sums[i] such that it is GREATER than sums[j] - k
     * This is what TreeMap.ceiling gives you.
     */
    private int findMaxSum(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0); // to handle the case when sum == k
        int prefixSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i]; // this is the running sum, sums[j]

            int diff = prefixSum - k;
            Integer ceiling = set.ceiling(diff);

            if (ceiling != null) {
                // ceiling value is basically sums[i].
                // we want the sum which is sums[j] - sums[i]
                // therefore we are subtracting the two values
                maxSum = Math.max(maxSum, prefixSum - ceiling);
            }
            set.add(prefixSum);
        }
        return maxSum;
    }

    private int kadens(int[] nums, int max) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            if (dp[i] >= max) return dp[i];
        }
        return dp[n - 1];
    }
}