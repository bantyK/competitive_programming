import java.util.Arrays;

//1685 https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
public class SumAbsoluteDifference {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SumAbsoluteDifference().getSumAbsoluteDifferences(new int[]{1,2,4,6,8,10})));
    }

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        // store the sum of numbers from left to right
        int[] prefixSum = new int[n];
        // store the sum of numbers from right  to left
        int[] suffixSum = new int[n];

        prefixSum[0] = nums[0];
        suffixSum[n - 1] = nums[n - 1];

        // populate the prefix sums
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            suffixSum[n - i - 1] = suffixSum[n - i] + nums[n - i - 1];
        }

        int[] res = new int[n];

        // see hint 2 for explanation
        // special handling from first and the last number
        res[0] = suffixSum[1] - (n - 1) * nums[0];
        res[n - 1] = (n - 1) * nums[n - 1] - prefixSum[n - 2];

        for (int i = 1; i < n - 1; i++) {
            int leftRes = (i * nums[i]) - prefixSum[i - 1];
            int rightRes = suffixSum[i + 1] - (n - i - 1) * nums[i];
            res[i] = leftRes + rightRes;
        }

        return res;
    }
}