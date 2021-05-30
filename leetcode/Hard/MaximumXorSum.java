import java.util.*;

// 1879 https://leetcode.com/problems/minimum-xor-sum-of-two-arrays/
public class MaximumXorSum {
    public static void main(String[] args) {
        MaximumXorSum obj = new MaximumXorSum();
        System.out.println(obj.minimumXORSum(new int[]{1, 2, 3}, new int[]{2, 3, 1}));
    }

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int[] remaining = new int[nums1.length];
        Arrays.fill(remaining, 1);
        return helper(nums1, nums2, 0, remaining, new HashMap<>());
    }


    /*

            1 2 3
            2 3 1

            (1,2) + (2,3) + (3,1)
            (1,2) + (2,1) + (3,3)
            (1,3) + (2,2) + (3,1)
            (1,3) + (2,1) + (3,2)
            (1,1) + (2,2) + (3,3) -> best



            best = 2
            [0, 1, 0]

            (1 ^ 2) + (2 ^ 3) + (3 ^ 1) => 2

            (1 ^ 2) + (2 ^ 1) + (3 ^ 3) => 2


     */

    private int helper(int[] nums1, int[] nums2, int index, int[] remaining, Map<String, Integer> cache) {
        if (index == nums1.length) {
            return 0;
        }
        String joined = join(remaining);
        String key = joined + "|" + index;
        if (cache.containsKey(key)) return cache.get(key);

        int best = Integer.MAX_VALUE;

        for (int i = 0; i < nums2.length; i++) {
            if (remaining[i] == 0) continue;
            remaining[i] = 0;
            best = Math.min(best, nums1[index] ^ nums2[i] + helper(nums1, nums2, index + 1, remaining, cache));
            remaining[i] = 1;
        }


        cache.put(key, best);
        return best;
    }

    private String join(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int num : arr) builder.append(num).append(",");
        return builder.toString();
    }
}