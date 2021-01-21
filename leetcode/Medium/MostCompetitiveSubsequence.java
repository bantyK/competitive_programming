import java.util.*;

// 1673 https://leetcode.com/problems/find-the-most-competitive-subsequence/
public class MostCompetitiveSubsequence {
    public static void main(String[] args) {
        MostCompetitiveSubsequence obj = new MostCompetitiveSubsequence();
        int[] nums = new int[] { 2, 4, 3, 3, 5, 4, 9, 6 };
        System.out.println(Arrays.toString(obj.mostCompetitive(nums, 4)));
    }

    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[k];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()] && nums.length - i + stack.size() > k) {
                // if the current array element is less than the top element at stack
                // there are enough element left in the array to fill k elements
                // then pop the top element.
                stack.pop();
            }

            if (stack.size() < k) {
                // we only need k elements in the stack.
                stack.push(i);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            res[i] = nums[stack.pop()];
        }

        return res;
    }
}