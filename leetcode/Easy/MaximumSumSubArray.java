package solutions.easy;

// 53 https://leetcode.com/problems/maximum-subarray/
public class MaximumSumSubArray {
    public static void main(String[] args) {
        MaximumSumSubArray obj = new MaximumSumSubArray();

        System.out.println(obj.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];

        int currentMaxSum = maxSum;
        for (int i = 1; i < nums.length; i++) {
            currentMaxSum = Math.max(nums[i], nums[i] + currentMaxSum);

            if (currentMaxSum > maxSum) {
                maxSum = currentMaxSum;
            }
        }

        return maxSum;
    }
}
