import java.util.*;

// 377 https://leetcode.com/problems/combination-sum-iv/
public class CombinationSum4 {
    public static void main(String[] args) {
        CombinationSum4 obj = new CombinationSum4();
        System.out.println(obj.combinationSum4(new int[]{1, 2, 3}, 4));
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        helper(nums, target, dp);
        return dp[target];
    }


    // brute force
    public int helper(int[] nums, int target, int[] dp) {
        if(dp[target] != -1) return dp[target];

        if(target == 0) {
            return 1;
        }

        int res = 0;

        for(int i = 0; i < nums.length; i++) {
            if(target >= nums[i]) {
                res += helper(nums, target - nums[i], dp);
            }
        }
        dp[target] = res;
        return res;
    }

}