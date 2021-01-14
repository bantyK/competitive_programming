import java.util.*;

// 1658 https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
public class MinOpsToReduceXToZero {
    public static void main(String[] args) {
        System.out.println(new MinOpsToReduceXToZero().minOperations(new int[]{5, 6, 7, 8, 9}, 20));
        System.out.println(new MinOpsToReduceXToZero().minOperations(new int[]{5, 6, 7, 8, 9}, 13));
    }

    // Find the maximum len subarray with sum equal to total - x
    public int minOperations(int[] nums, int x) {
        // int target = -x;

        int total = 0;
        for (int num : nums) total += num;

        int target = total - x;

        if (target == 0) return nums.length; // the sum of all numbers is equal to x.

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                // The total sum from 0 to the current position(i) is sum.
                // If there is an index in the array such that the sum at that index is
                // sum - target. Lets say that index is j.
                // This means that the sum from j to i is target

                maxLen = Math.max(maxLen, i - map.get(sum - target));
            }
            map.put(sum, i);
        }

        return maxLen == Integer.MIN_VALUE ? -1 : nums.length - maxLen;
    }
}
