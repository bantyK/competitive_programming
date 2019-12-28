import java.util.Arrays;

public class SubsetSetCount {
    public static void main(String[] args) {
        SubsetSetCount obj = new SubsetSetCount();
        int[] nums = {1, 1, 2, 3};
        int sum = 4;

        System.out.println(countSubset(nums, sum));
        System.out.println(countSubsetTopDown(nums, sum));
    }

    /**
     * @param nums
     * @param k
     * @return the count of subsets whose sum is equal to k
     * <p>
     * Recursive approach
     */
    public static int countSubset(int[] nums, int k) {
        return countSubsetRecursive(nums, k, 0);
    }

    private static int countSubsetRecursive(int[] nums, int sum, int index) {
        if (sum == 0) {
            return 1;
        }

        if (index >= nums.length) {
            return 0;
        }

        int count1 = 0;
        if (nums[index] <= sum) {
            count1 = countSubsetRecursive(nums, sum - nums[index], index + 1);
        }
        int count2 = countSubsetRecursive(nums, sum, index + 1);


        return count1 + count2;
    }


    /**
     * Top Bottom approach
     *
     * @param nums
     * @param sum
     * @return
     */
    public static int countSubsetTopDown(int[] nums, int sum) {
        int n = nums.length;
        Integer[][] dp = new Integer[n + 1][sum + 1];

        return topBottom(dp, nums, sum, 0);
    }

    private static int topBottom(Integer[][] dp, int[] nums, int sum, int index) {
        if (sum == 0) {
            return 1;
        }

        if (index >= nums.length) return 0;

        if (dp[index][sum] != null) return dp[index][sum];

        int count1 = 0;
        if (nums[index] <= sum) {
            count1 = topBottom(dp, nums, sum - nums[index], index + 1);
        }

        int count2 = topBottom(dp, nums, sum, index + 1);

        dp[index][sum] = count1 + count2;

        return dp[index][sum];
    }

    /**
     * Bottom up approach
     *
     * @param nums
     * @param sum
     * @return
     */
    public static int countSubsetBottomUp(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n][sum + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int s = 1; s <= sum; s++) {
            if (s <= nums[0]) {
                dp[0][s] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                dp[i][s] = dp[i - 1][s];

                if (s <= nums[i]) {
                    dp[i][s] += dp[i - 1][s - nums[i]];
                }
            }
        }

        return dp[n - 1][sum];
    }
}

