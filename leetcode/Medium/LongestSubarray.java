import java.util.*;

//1438 https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
public class LongestSubarray {
    public static void main(String[] args) {
        LongestSubarray obj = new LongestSubarray();

        System.out.println(obj.longestSubarrayUsingTreeMap(new int[]{8, 2, 4, 7}, 4));
        System.out.println(obj.longestSubarrayUsingTreeMap(new int[]{10, 1, 2, 4, 7, 2}, 5));
        System.out.println(obj.longestSubarrayUsingTreeMap(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }

    public int longestSubarrayUsingTreeMap(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < nums.length; right++) {
            final int rightNum = nums[right];
            map.put(rightNum, map.getOrDefault(rightNum, 0) + 1);

            // lastKey will return the maxKey and firstKey will return the minKey because treemap keeps the keys sorted
            while (map.lastKey() - map.firstKey() > limit) {
                int leftNum = nums[left++];
                map.put(leftNum, map.get(leftNum) - 1);
                if (map.get(leftNum) == 0) map.remove(leftNum);
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Solution using Deque
    public int longestSubarrayUsingDeque(int[] nums, int limit) {
        // the first element of maxD will contain the maximum element of a window
        Deque<Integer> maxD = new ArrayDeque<>();

        // the first element of minD will contain the minimum element of a window
        Deque<Integer> minD = new ArrayDeque<>();

        int left = 0;
        int n = nums.length;
        int maxLen = 0;
        for (int right = 0; right < n; right++) {
            while (!maxD.isEmpty() && maxD.peekLast() < nums[right]) {
                // pop all the element from the last which are less than the current element of the window
                // those elements does not matter anymore
                maxD.pollLast();
            }

            while (!minD.isEmpty() && minD.peekLast() > nums[right]) {
                // pop all the element from the last which are greater than the current element of the window
                minD.pollLast();
            }

            maxD.add(nums[right]);
            minD.add(nums[right]);

            while (maxD.peekFirst() - minD.peekFirst() > limit) {
                // leftNum is the number which is going out of the window.
                int leftNum = nums[left++];

                if (maxD.peekFirst() == leftNum) {
                    // if the number which is going out is the maximum number, pop from the beginning of the max deque
                    maxD.pollFirst();
                }
                if (minD.peekFirst() == leftNum) {
                    // if the number which is going out is the minimum number, pop from the beginning of the min deque
                    minD.pollFirst();
                }
            }
            int len = right - left + 1;
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}
