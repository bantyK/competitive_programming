import java.util.*;

//456 https://leetcode.com/problems/132-pattern/
public class Pattern132 {
    public static void main(String[] args) {
        Pattern132 obj = new Pattern132();
        System.out.println(obj.find132pattern(new int[]{2, 3, 1, 2}));
        System.out.println(obj.find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(obj.find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(obj.find132pattern(new int[]{-1, 0, 4, 3}));
        System.out.println(obj.find132pattern(new int[]{3, 5, 0, 3, 4}));
    }

    // O(n3)
    public boolean find132pattern1(int[] nums) {
        int len = nums.length;
        if (len < 3) return false;

        for (int i = 0; i <= len - 3; i++) {
            for (int j = i + 1; j <= len - 2; j++) {
                if (nums[j] > nums[i]) {
                    for (int k = j + 1; k < len; k++) {
                        if (nums[i] < nums[k] && nums[k] < nums[j]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // O(n2)
    public boolean find132pattern2(int[] nums) {
        int len = nums.length;
        if (len < 3) return false;

        int min = Integer.MAX_VALUE; // this will store nums[i]

        for (int j = 0; j < len; j++) {
            min = Math.min(min, nums[j]);

            if (min == nums[j]) continue;

            for (int k = len - 1; k > j; k--) {
                if (nums[k] > min && nums[k] < nums[j]) return true;
            }
        }

        return false;
    }

    // O(n) using stack for tracking nums[k]
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        if (len < 3) return false;
        // all numbers in the stack are nums[k]
        Stack<Integer> stack = new Stack<>();
        int[] min = new int[len];
        min[0] = nums[0];

        for (int i = 1; i < len; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }

        // starting from end to satisfy i < j < k criteria
        for (int j = len - 1; j >= 0; j--) {
            // This is to satisfy the first criteria nums[j] > nums[i]
            // nums[j] must be greater than nums[i] first to proceed further
            if (nums[j] > min[j]) {

                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    // all the elements in the stack which are less than min[j] are useless and can simply be removed
                    // for a valid triplet,nums[k] must be greater than nums[i] which are represented by min[j]
                    stack.pop();
                }

                // here the top element in the stack is more than nums[i] which validate first criteria
                // if the top element is less than nums[j], than that validates the second criteria also
                // at this point we found a valid triplet so return true
                if (!stack.isEmpty() && stack.peek() < nums[j]) return true;

                stack.push(nums[j]);
            }
        }

        return false;
    }
}