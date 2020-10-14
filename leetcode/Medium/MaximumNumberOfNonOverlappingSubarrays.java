import java.util.*;

// 1546 https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
public class MaximumNumberOfNonOverlappingSubarrays {
    public static void main(String[] args) {
        MaximumNumberOfNonOverlappingSubarrays obj = new MaximumNumberOfNonOverlappingSubarrays();
    }

    public int maxNonOverlapping(int[] nums, int target) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int lastUsedSubarrayEnd = -1;
        int sum = 0;
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - target) && map.get(sum - target) >= lastUsedSubarrayEnd) {
                count++;
                // the new subarray should start after the last subarray end to ensure non-overlapping
                lastUsedSubarrayEnd = i;
            }
            map.put(sum, i);
        }

        return count;
    }
}