package prefix;

import java.util.*;

// 560 https://leetcode.com/problems/subarray-sum-equals-k/
public class SubarraySum {
    public static void main(String[] args) {
        SubarraySum obj = new SubarraySum();
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int k = 0;
        System.out.println(obj.subarraySum2(nums, k));
    }

    /**
     * Brute force.
     * Find all subarrays and calculate the sum
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum = currentSum + nums[j];
                if (currentSum == k) {
                    count += 1;
                }
            }

        }
        return count;
    }

    /**
     * optimised solution using a hash map
     *
     * @param a
     * @param k
     * @return
     */
    public int subarraySum2(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int currentSum = 0;
        for (int i = 0; i < a.length; i++) {
            currentSum = currentSum + a[i];
            if (currentSum == k) {
                count += 1;
            }

            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }

            int prevCount = map.getOrDefault(currentSum, 0);
            map.put(currentSum, prevCount + 1);
        }

        return count;
    }
}
