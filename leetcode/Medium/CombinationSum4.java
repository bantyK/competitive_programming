import java.util.Arrays;

//377 https://leetcode.com/problems/combination-sum-iv/
public class CombinationSum4 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        System.out.println(new CombinationSum4().combinationSum4(nums, 35));
    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);

        int[] dp = new int[target + 1];
        dp[0] = 1;


        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}