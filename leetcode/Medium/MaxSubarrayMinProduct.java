import java.util.*;

// https://leetcode.com/problems/maximum-subarray-min-product/submissions/
public class MaxSubarrayMinProduct {
    public static final int MOD = (int) (Math.pow(10, 9) + 7);

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        int[] ngl = smallerElementToLeft(nums);
        int[] ngr = smallerElementToRight(nums);

        long[] presum = new long[n + 1];
        presum[1] = nums[0];

        for (int i = 1; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }

        long maxProduct = 0;

        for (int i = 0; i < n; i++) {
            int left = ngl[i];
            int right = ngr[i];
            long sum = presum[right] - presum[left + 1];
            long product = sum * nums[i];
            maxProduct = Math.max(maxProduct, product);
        }
        return (int) (maxProduct % MOD);
    }

    private int[] smallerElementToRight(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, n);
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) res[i] = stack.peek();

            stack.push(i);
        }

        return res;
    }

    /**
     * for each element in the array, returns the index of next greater element to its left
     */
    private int[] smallerElementToLeft(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) res[i] = stack.peek();
            stack.push(i);
        }
        return res;
    }

}