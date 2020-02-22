package sliding;

import java.util.*;

/**
 * Returns the number of sub-arrays which contains at-most K distinct elements.
 */
public class AtMostKDistinct {
    public static void main(String[] args) {
        AtMostKDistinct obj = new AtMostKDistinct();
        final int count = obj.atmostKDistinct(new int[]{1, 2, 1, 2, 3}, 2);
        System.out.println(count);
    }

    public int atmostKDistinct(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0; // edge case check(blank array)

        int left = 0; // start index of the window.
        int right = 0; // end index of the window
        int res = 0; // contains the number of sub-arrays which is finally returned

        // used to track the number of distinct elements encountered so far
        Map<Integer, Integer> countMap = new HashMap<>();

        while (right < n) {
            int rightNum = nums[right];

            int prevValue = countMap.getOrDefault(rightNum, 0);
            countMap.put(rightNum, prevValue + 1);

            while (countMap.size() > k) {
                int leftNum = nums[left++];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    countMap.remove(leftNum);
                }
            }

            res += (right - left + 1);

            right++; // this should be incremented after calculation of res.
        }

        return res;
    }
}
