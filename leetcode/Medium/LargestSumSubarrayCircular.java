// 918 https://leetcode.com/problems/maximum-sum-circular-subarray/

public class LargestSumSubarrayCircular {
    public static void main(String[] args) {
        LargestSumSubarrayCircular obj = new LargestSumSubarrayCircular();

        final int i = obj.maxSubarraySumCircular(new int[]{-10,-7,9,-7,6,9,-9,-4,-8,-5});

        System.out.println(i);
    }

    public int maxSubarraySumCircular(int[] A) {
        // this is the case when the maxSum is present in the middle of the array, not going over a circle
        int maxSumOption1 = maxSum(A);

        // this is the case when the maxSum goes over the cirle, instead of going over the array, we are calculating the min
        // sum. The array which does not come under maxSum will be minSum because the total is a constant quantity.
        // So if maxSum is going over a circle, the minSum will not. So calculate the min sum and subtract with totalSum
        int total = totalSum(A);
        int maxSumOption2  = total - minSum(A);

        // if all the number are negative, in that case the min sum will be the total array sum,in that case the maxSum will
        // be the largest number.

        if(maxSumOption1 > 0) {
            return Math.max(maxSumOption1, total - maxSumOption2);
        }

        return maxSumOption1;
    }

    private int totalSum(int[] nums) {
        int total = 0;
        int highest = nums[0];
        for(int num : nums) {
            total += num;
            highest = Math.max(highest, num);
        }
        return total;
    }

    private int minSum(int[] nums) {
        int globalMin = nums[0];
        int localMin = nums[0];

        for(int i = 1; i < nums.length; i++) {
            localMin = Math.min(nums[i], nums[i] + localMin);
            globalMin = Math.min(globalMin, localMin);
        }
        return globalMin;
    }

    private int maxSum(int[] nums) {
        int globalMax = nums[0];
        int localMax = nums[0];

        for(int i = 1; i < nums.length; i++) {
            localMax = Math.max(localMax + nums[i], nums[i]);
            globalMax = Math.max(globalMax, localMax);
        }

        return globalMax;
    }
}
