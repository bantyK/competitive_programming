// 1695 https://leetcode.com/problems/maximum-erasure-value/
public class MaximumErasureValue {

	public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        Set<Integer> unique = new HashSet<>();
        int maxSum = 0;

        int runningSum = 0;
        for (int right = 0; right < n; ) {
            while (right < n && !unique.contains(nums[right])) {
                unique.add(nums[right]);
                runningSum += nums[right];
                right++;
            }
            maxSum = Math.max(maxSum, runningSum);

            while (left < n && right < n && unique.contains(nums[right])) {
                unique.remove(nums[left]);
                runningSum -= nums[left];
                left++;
            }
        }

        return maxSum;
    }
}