// 1877 https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
class MinimizeMaximumPairSum {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        
        int left = 0, right = nums.length - 1;
        
        while(left < right) {
            int sum = nums[left] + nums[right];
            maxSum = Math.max(sum, maxSum);
            left++;
            right--;
        }
        
        return maxSum;
    }
}
