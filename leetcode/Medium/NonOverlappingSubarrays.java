import java.util.*;

// 1477 https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
public class NonOverlappingSubarrays {
    public static void main(String[] args) {
        NonOverlappingSubarrays obj = new NonOverlappingSubarrays();
        System.out.println(obj.minSumOfLengths(new int[]{1, 2, 1, 4}, 3));
//        System.out.println(obj.minSumOfLengths(new int[]{3, 1, 1, 1, 4, 5, 1, 2, 1}, 3));
//        System.out.println(obj.minSumOfLengths(new int[]{3, 1, 1, 1, 4}, 3));
//        System.out.println(obj.minSumOfLengths(new int[]{3, 1, 1, 4}, 3));
//        System.out.println(obj.minSumOfLengths(new int[]{7, 3, 4, 7}, 7));
//        System.out.println(obj.minSumOfLengths(new int[]{1, 2, 2, 3, 2, 6, 7, 2, 1, 4, 8}, 5));
//        System.out.println(obj.minSumOfLengths(new int[]{3,2,2,4,3}, 3));
//        System.out.println(obj.minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
//        System.out.println(obj.minSumOfLengths(new int[]{1, 6, 1}, 7));
    }

    public int minSumOfLengths(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int left = 0;
        int sum = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left++];
            }

            if (sum == target) {
                dp[right] = right - left + 1;
            }
        }

        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                int prevIndex = i - dp[i];

                // we are going dp[i] len backwards, and since dp[i] contains the length of subarray with target sum
                // going dp[i] back ensures that we are looking at non overlapping array
                // dp[prevIndex] = length of non-overlapping subarray with sum = target
                if (prevIndex >= 0 && dp[prevIndex] != Integer.MAX_VALUE) {
                    minLen = Math.min(minLen, dp[i] + dp[prevIndex]);
                }
            }

            // this is done to make sure that when we do i - dp[i] it will always give the minimum len subarray up until that point
            if (i > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
        }

        if (minLen == Integer.MAX_VALUE) return -1;
        return minLen;
    }
}
