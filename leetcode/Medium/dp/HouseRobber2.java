package fibonaci;

//213 https://leetcode.com/problems/house-robber-ii/
public class HouseRobber2 {

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int first = first(nums, 0, nums.length - 2);
        int second = second(nums, 1, nums.length - 1);

        return Math.max(first, second);
    }

    private int first(int[] nums, int startIndex, int lastIndex) {
        int[] dp = new int[nums.length - 1];
        dp[0] = nums[startIndex];
        dp[1] = Math.max(nums[startIndex], nums[startIndex + 1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[lastIndex];
    }

    private int second(int[] nums, int startIndex, int lastIndex) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        dp[1] = nums[startIndex];
        dp[2] = Math.max(nums[startIndex], nums[startIndex + 1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[lastIndex];
    }
}
