import java.util.*;

// 549 https://leetcode.com/problems/longest-harmonious-subsequence/
public class LongestHarmoniousSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 2, 3, 4}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 1, 1, 1}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 2, 1}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 20, 10}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{-3, -1, -1, -1, -3, -2}));
        System.out.println(new LongestHarmoniousSubsequence().findLHS(new int[]{1, 2, 1, 3, 0, 0, 2, 2, 1, 3, 3}));
    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int res = 0;
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }
}
