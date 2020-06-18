package sw;

import java.util.*;

// 1248 https://leetcode.com/problems/count-number-of-nice-subarrays/
public class NiceSubarrays {
    public static void main(String[] args) {
        NiceSubarrays obj = new NiceSubarrays();
//        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 1, 1, 2, 2, 1, 2}, 2));
//        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));

        System.out.println(obj.countNiceSubarrays(new int[]{2, 2, 1, 1, 2, 2, 1, 2}, 2));
        System.out.println(obj.countNiceSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    /**
     * This algorithm discussed here
     * https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/508217/C%2B%2B%3A-Visual-explanation.-O(1)-space.-Two-pointers
     */

    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0;
        int oddCount = 0;
        int count = 0;
        int total = 0;
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            if (num % 2 == 1) {
                count = 0;
                oddCount++;
            }

            while (oddCount >= k) {
                int leftNum = nums[left++];
                if (leftNum % 2 == 1) {
                    oddCount--;
                }
                count++;
            }

            total += count;
        }

        return total;
    }

    /**
     * Method 2: Discussed here
     * https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/419545/JavaC%2B%2B-with-picture-deque
     *
     * The approach is same, the deque is an alternative to the 2 pointer strategy
     */
    public int countNiceSubarrays(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        deque.add(-1);
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                deque.add(i);
            }
            if (deque.size() > k + 1) {
                deque.pop();
            }
            if (deque.size() == k + 1) {
                int i1 = deque.get(1);
                int i0 = deque.get(0);
                res += i1 - i0;
            }
        }


        return res;
    }
}
