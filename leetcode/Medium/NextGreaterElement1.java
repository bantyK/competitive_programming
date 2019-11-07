package solutions.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/next-greater-element-i/
public class NextGreaterElement1 {
    public static void main(String[] args) {
        NextGreaterElement1 obj = new NextGreaterElement1();
        int[] nums1 = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};

        int[] res = obj.nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums2nextGreaterMap = new HashMap<>();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums2.length; ++i) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                nums2nextGreaterMap.put(nums2[stack.pop()], nums2[i]);
            }

            stack.push(i);
        }
        int[] res = new int[nums1.length];

        for (int i = 0; i < nums1.length; ++i) {
            res[i] = nums2nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return res;

    }
}
