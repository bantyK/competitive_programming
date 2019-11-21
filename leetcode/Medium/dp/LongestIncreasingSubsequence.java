import java.util.*;
//300 https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int len = lengthOfLIS(new int[]{7, 6, 5, 4, 3, 2, 1});
        System.out.println(len);
    }

    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return 1;

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);
        int max = 1;

        for(int j = 1; j < dp.length; j++) {
            for(int i = 0; i < j; i++) {
                if(nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], 1+dp[i]);
                    if(dp[j] > max) {
                        max = dp[j];
                    }
                }
            }
        }

        return max;
    }
    
}