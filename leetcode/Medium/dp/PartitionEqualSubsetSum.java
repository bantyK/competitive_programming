// 416 https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % 2 == 1) return false;

        Boolean[][] dp = new Boolean[nums.length][sum / 2 + 1];
        return topBottom(nums, sum / 2, 0, dp);
    }

    private static boolean topBottom(int[] nums, int sum, int currentIndex, Boolean[][] dp) {
        if (0 == sum) return true;

        if (currentIndex >= nums.length) return false;

        if (dp[currentIndex][sum] == null) {
            if (nums[currentIndex] <= sum) {
                if (topBottom(nums, sum - nums[currentIndex], currentIndex + 1, dp)) {
                    dp[currentIndex][sum] = true;
                    return true;
                }
            }

            dp[currentIndex][sum] = topBottom(nums, sum, currentIndex + 1, dp);
        }

        return dp[currentIndex][sum];
    }


    // Bottom up approach
    private static boolean bottomUp(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) sum += i;

        if (sum % 2 == 1) return false;

        sum /= 2;


        boolean[][] dp = new boolean[n][sum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int s = 1; s <= sum; s++) {
            dp[0][s] = (nums[0] == s);
        }

        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                if (s >= nums[i]) {
                    dp[i][s] = dp[i - 1][s] || dp[i - 1][s - nums[i]];
                } else {
                    dp[i][s] = dp[i - 1][s];
                }
            }
        }

        return dp[n - 1][sum];
    }


    // recursive approach (2 ^ n)
    private static boolean helper(int[] nums, int currentSum, int totalSum, int currentIndex) {
        if (currentSum == totalSum) {
            return true;
        }

        if (currentIndex >= nums.length) return false;

        if (nums[currentIndex] <= totalSum) {
            if (helper(nums, currentSum + nums[currentIndex], totalSum, currentIndex + 1)) {
                return true;
            }
        }
        return helper(nums, currentSum, totalSum, currentIndex + 1);
    }


}