package lcs;

// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence/0/

import java.util.HashMap;
import java.util.Map;

public class MaximumSumLIS {
    public static void main(String[] args) {
        MaximumSumLIS obj = new MaximumSumLIS();
        int[] nums = {1, 101, 2, 3, 100, 4, 5};
        int[] dp = {1, 101, 2, 3, 100, 4, 5};


        System.out.println(maxSumLIS(nums, dp));
        System.out.println(maxSumLIS(nums, 0, -1, 0, new HashMap<>()));
    }

    private static int maxSumLIS(int[] nums, int[] dp) {
        int maxSum = Integer.MIN_VALUE;
        for (int j = 1; j < nums.length; j++) {
            int sum = dp[j];
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + sum);
                }
            }
            maxSum = Math.max(dp[j], maxSum);
        }

        return maxSum;
    }

    private static int maxSumLIS(int[] nums, int currentIndex, int previousIndex, int sum, Map<String, Integer> dp) {
        if (currentIndex == nums.length) {
            return sum;
        }

        String key = currentIndex + "|" + previousIndex + "|" + sum;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int sum1 = 0;
        if (previousIndex == -1 || nums[currentIndex] > nums[previousIndex]) {
            sum1 = maxSumLIS(nums, currentIndex + 1, currentIndex, sum + nums[currentIndex], dp);
        }

        int sum2 = maxSumLIS(nums, currentIndex + 1, previousIndex, sum, dp);

        dp.put(key, Math.max(sum1, sum2));
        return dp.get(key);
    }
}
