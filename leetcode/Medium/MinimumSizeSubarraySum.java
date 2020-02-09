//209 https://leetcode.com/problems/minimum-size-subarray-sum/submissions/
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum obj = new MinimumSizeSubarraySum();
        System.out.println(obj.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }


    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int minLen = nums.length + 1;
        int runningSum = 0;

        for (int end = 0; end < nums.length; end++) {
            runningSum += nums[end];

            while (runningSum >= s) {
                minLen = Math.min(minLen, end - start + 1);
                runningSum -= nums[start];
                start++;
            }
        }


        return minLen > nums.length ? 0 : minLen;
    }

}
