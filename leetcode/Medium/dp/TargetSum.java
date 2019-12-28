import java.util.*;

// 494 https://leetcode.com/problems/target-sum/
public class TargetSum {
    public static void main(String[] args) {
        TargetSum obj = new TargetSum();
        int targetSumWays = obj.findTargetSumWays(new int[]{42, 24, 30, 14, 38, 27, 12, 29, 43, 42, 5, 18, 0, 1, 12, 44, 45, 50, 21, 47}, 38);
        System.out.println(targetSumWays);
    }

    public int findTargetSumWays(int[] nums, int S) {
        Map<String, Integer> dp = new HashMap<>();
        return helper(nums, 0, 0, S, dp);
    }

    private int helper(int[] nums, int index, int currentSum, int S, Map<String, Integer> dp) {
        String key = index + "->" + currentSum;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        if (index == nums.length) {

            if (currentSum == S) {
                return 1;
            } else {
                return 0;
            }
        }

        int currentNum = nums[index];
        int plus = helper(nums, index + 1, currentSum + currentNum, S, dp);
        int minus = helper(nums, index + 1, currentSum - currentNum, S, dp);
        dp.put(key, plus + minus);

        return plus + minus;
    }
}
