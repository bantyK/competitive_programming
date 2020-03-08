package sliding;

import java.util.*;

//992 https://leetcode.com/problems/subarrays-with-k-different-integers/
// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235235/C%2B%2BJava-with-picture-prefixed-sliding-window

public class GoodSubarrays {
    public static void main(String[] args) {
        GoodSubarrays obj = new GoodSubarrays();
        int[] a = new int[]{1, 2, 1, 2, 3};
        int k = 2;
        int num = obj.subarraysWithKDistinct2(a, k);
        System.out.println(num);
    }

    /**
     * Approach 2: Calculate the number of subarrays with ATMOST K distinct elements and number of subarrays with ATMOST (K - 1) distinct elements.
     * The difference between the two will give the number of subarrays with exactly k distinct elements.
     *
     * @param a
     * @param k
     * @return
     */
    public int subarraysWithKDistinct2(int[] a, int k) {
        int res1 = subarraysWithAtmostKDistinct(a, k);
        System.out.println(res1);
        int res2 = subarraysWithAtmostKDistinct(a, k - 1);
        System.out.println(res2);
        return res1 - res2;
    }


    /**
     * @param a
     * @param k
     * @return the number of subarrays with ATMOST K distinct elements
     */
    private int subarraysWithAtmostKDistinct(int[] a, int k) {
        int left = 0;
        int res = 0;
        int right = 0;

        Map<Integer, Integer> countMap = new HashMap<>();

        while (right < a.length) {
            int rightNum = a[right];
            countMap.put(rightNum, countMap.getOrDefault(rightNum, 0) + 1);

            while (countMap.size() > k) {
                int leftNum = a[left++];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    countMap.remove(leftNum);
                }
            }

            res += right - left + 1;
            right++;
        }

        return res;
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        int res = 0, prefix = 0;
        int start = 0;
        int distinctCount = 0;
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int right = 0; right < A.length; right++) {
            int rightNum = A[right];

            if (!countMap.containsKey(rightNum) || countMap.get(rightNum) == 0) {
                distinctCount++;
            }

            countMap.put(rightNum, countMap.getOrDefault(rightNum, 0) + 1);

            //reset
            if (distinctCount > K) {
                int startNum = A[start];
                start++;
                prefix = 0;
                countMap.put(startNum, countMap.get(startNum) - 1);
                distinctCount--;
            }

            while (countMap.get(A[start]) > 1) {
                int startNum = A[start++];
                countMap.put(startNum, countMap.get(startNum) - 1);
                prefix++;
            }

            if (distinctCount == K) {
                res += prefix + 1;
            }

        }

        return res;
    }
}
