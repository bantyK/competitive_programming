import java.util.*;

public class MinSubsetSumDifference {
    public static void main(String[] args) {
        MinSubsetSumDifference obj = new MinSubsetSumDifference();

        int[] num = {1, 2, 3, 9};
        System.out.println(minDifference(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minDifference(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minDifference(num));


        num = new int[]{1, 2, 3, 9};
        System.out.println(minDifferenceMemo(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minDifferenceMemo(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minDifferenceMemo(num));


        num = new int[]{1, 2, 3, 9};
        System.out.println(minDifferenceDp(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minDifferenceDp(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minDifferenceDp(num));
    }

    /**
     * @param nums
     * @return the minimum difference between 2 subsets
     * Recursive approach without any memoization.
     * Time complexity O(2 ^ n)
     */
    private static int minDifference(int[] nums) {
        return minDifferenceRecursive(nums, 0, 0, 0);
    }

    private static int minDifferenceRecursive(int[] nums, int index, int sum1, int sum2) {
        if (index == nums.length) {
            return Math.abs(sum1 - sum2);
        }

        int diff1 = minDifferenceRecursive(nums, index + 1, sum1 + nums[index], sum2);
        int diff2 = minDifferenceRecursive(nums, index + 1, sum1, sum2 + nums[index]);

        return Math.min(diff1, diff2);
    }


    /**
     * @param nums
     * @return the minimum difference between 2 subsets
     * <p>
     * Dynamic programming
     * Top-Bottom approach
     * <p>
     * Time complexity O(n * S)
     */

    private static int minDifferenceMemo(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        int n = nums.length;
        Integer[][] dp = new Integer[n][sum + 1];

        return minDifferenceTopDown(dp, nums, 0, 0, 0);
    }

    private static int minDifferenceTopDown(Integer[][] dp, int[] nums, int sum1, int sum2, int index) {
        if (index == nums.length) {
            return Math.abs(sum1 - sum2);
        }

        if (dp[index][sum1] == null) {
            int diff1 = minDifferenceTopDown(dp, nums, sum1 + nums[index], sum2, index + 1);
            int diff2 = minDifferenceTopDown(dp, nums, sum1, sum2 + nums[index], index + 1);

            dp[index][sum1] = Math.min(diff1, diff2);
        }

        return dp[index][sum1];
    }

    /**
     * Bottom up approach
     *
     * @param nums
     * @return the minimum difference between 2 subsets
     */

    private static int minDifferenceDp(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        int n = nums.length;

        int sum = total / 2;
        boolean[][] dp = new boolean[n][sum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int s = 1; s <= sum; s++) {
            dp[0][s] = nums[0] == s;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if (dp[n - 1][sum]) return 0;

        int minSum1 = sum;
        for (int j = sum; j >= 0; j--) {
            if (dp[n - 1][j]) {
                minSum1 = j;
                break;
            }
        }

        int sum2 = total - minSum1;

        return Math.abs(minSum1 - sum2);
    }
}
