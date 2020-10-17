import java.util.*;

// 189 https://leetcode.com/problems/rotate-array/
public class RotateArray {
    public static void main(String[] args) {
        RotateArray obj = new RotateArray();
        int[] nums = new int[]{1, 2, 3, 4, 5};
        obj.rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }


    ////////// No extra space //////////
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % nums.length;

        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    //////////////// Extra Space ////////////////

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % nums.length;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            stack.push(nums[nums.length - i - 1]);
        }

        int j = n - k - 1;
        int i = n - 1;
        while (j >= 0) {
            nums[i--] = nums[j--];
        }

        i = 0;
        while (!stack.isEmpty()) {
            nums[i++] = stack.pop();
        }
    }

    ////////// Brute Force //////////
    public void rotateArray(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            rotateOnce(nums);
        }
    }

    private void rotateOnce(int[] nums) {
        int temp = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
        nums[nums.length - 1] = temp;
    }

}
