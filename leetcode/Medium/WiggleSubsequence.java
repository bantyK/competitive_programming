package unboundedknapsack;

import java.util.Arrays;

//376 https://leetcode.com/problems/wiggle-subsequence/
public class WiggleSubsequence {

    public static void main(String[] args) {
        WiggleSubsequence obj = new WiggleSubsequence();

        int[] nums = new int[]{3, 3, 3, 2, 5};
        System.out.println(obj.wiggleMaxLength(nums));
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int maxLen = 1;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        Boolean prev = null;

        for (int i = 1; i < n; i++) {
            boolean increasing = nums[i] - nums[i - 1] > 0;
            if (nums[i] == nums[i - 1]) {
                dp[i] = dp[i - 1];
            } else if (prev == null) {
                dp[i] = dp[i - 1] + 1;
                prev = increasing;
            } else if (prev && increasing) {
                dp[i] = dp[i - 1];
                prev = increasing;
            } else if (!prev && !increasing) {
                dp[i] = dp[i - 1];
                prev = increasing;
            } else {
                dp[i] = 1 + dp[i - 1];
                prev = increasing;
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n;

        int start = 0;
        while(start < n && nums[start] == nums[0]) {
            start++;
        }
        if(start == n) return 1;
        int len = 1;

        int prev = nums[0];
        boolean increasing = nums[start] > nums[0];

        for(int i = start; i < n; i++) {
            if((increasing && nums[i] > prev) || (!increasing && nums[i] < prev)) {
                len += 1;
                increasing = !increasing;
            }
            prev = nums[i];
        }

        return len;
    }

}
