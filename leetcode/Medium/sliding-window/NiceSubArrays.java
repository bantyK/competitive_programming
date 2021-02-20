import java.util.*;

//1248 https://leetcode.com/problems/count-number-of-nice-subarrays/

public class NiceSubArrays {
    public static void main(String[] args) {
        NiceSubArrays obj = new NiceSubArrays();
        System.out.println(obj.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3) == 2);
        System.out.println(obj.numberOfSubarrays(new int[]{2, 4, 6}, 1) == 0);
        System.out.println(obj.numberOfSubarrays(new int[]{1, 1, 1, 1}, 1) == 4);
        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2) == 16);
        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 1, 2, 1, 2, 2}, 2) == 9);
        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 1, 1, 2, 2}, 2) == 9);
        System.out.println(obj.numberOfSubarrays(new int[]{2, 2, 1, 1, 2, 1}, 2) == 7);
    }

    /**
     * @param nums
     * @param k
     * @return total number of sub arrays with K odd numbers.
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int res = 0;
        int odd = 0;
        int count = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                odd++;
                if (odd >= k) {
                    count = 1;
                    while (nums[left++] % 2 == 0) {
                        count++;
                    }
                    res += count;
                }
            } else if (odd >= k) {
                res += count;
            }
        }
        return res;
    }
}
