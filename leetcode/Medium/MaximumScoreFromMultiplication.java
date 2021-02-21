// 1770 https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
public class MaximumScoreFromMultiplication {
    int[] nums;
    int[] multipliers;

    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        int m = multipliers.length;
        Integer[][] dp = new Integer[m][m];
        return helper(0, nums.length - 1, 0, dp);
    }

    // Right can be derived from left, so no need to consider that in dp array
    // The maximum range is M, to optimise space.
    private int helper(int left, int right, int multiplierIndex, Integer[][] dp) {
        if (multiplierIndex == multipliers.length) {
            return 0;
        }
        if (dp[left][multiplierIndex] != null) return dp[left][multiplierIndex];

        int temp = nums[left] * multipliers[multiplierIndex];
        int chooseLeft = temp + helper(left + 1, right, multiplierIndex + 1, dp);

        temp = nums[right] * multipliers[multiplierIndex];
        int chooseRight = temp + helper(left, right - 1, multiplierIndex + 1, dp);

        return dp[left][multiplierIndex] = Math.max(chooseLeft, chooseRight);
    }
}
