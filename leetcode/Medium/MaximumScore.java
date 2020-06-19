package sw;

// 1423 https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class MaximumScore {
    public static void main(String[] args) {
        MaximumScore obj = new MaximumScore();
        System.out.println(obj.maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
        System.out.println(obj.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(obj.maxScore(new int[]{100, 40, 17, 9, 73, 75}, 3));
    }

    /**
     * Find the smallest subarray sum of length nums.length - k
     * <p>
     * Subtract this sum with the total sum of the array, that will give the maximum sum which will contain
     * the elements in the either ends.
     */
    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int requiredSize = n - k;
        int totalSum = 0;
        int left = 0;
        int minimumSum = Integer.MAX_VALUE;
        int currentSum = 0;

        for (int right = 0; right < nums.length; ++right) {
            totalSum += nums[right];
            currentSum += nums[right];

            if(right - left + 1 == requiredSize) {
                minimumSum = Math.min(minimumSum, currentSum);
                currentSum -= nums[left++];
            }
        }

        return totalSum - (minimumSum != Integer.MAX_VALUE ? minimumSum : 0);
    }


    /**
     * Solved using DP. Gives TLE in Leetcode
     */
    public int maxScoreDP(int[] nums, int k) {
        Integer[][] dp = new Integer[nums.length][nums.length];
        return helper(nums, k, 0, nums.length - 1, 0, dp);
    }

    private int helper(int[] nums, int k, int start, int end, int sum, Integer[][] dp) {
        if (0 == k) {
            return sum;
        }

        int fromLeft = helper(nums, k - 1, start + 1, end, sum + nums[start], dp);
        int fromRight = helper(nums, k - 1, start, end - 1, sum + nums[end], dp);
        return Math.max(fromLeft, fromRight);
    }


}
