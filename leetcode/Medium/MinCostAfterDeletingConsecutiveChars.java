// 1578 https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
public class MinCostAfterDeletingConsecutiveChars {
    public static void main(String[] args) {
        MinCostAfterDeletingConsecutiveChars obj = new MinCostAfterDeletingConsecutiveChars();
        System.out.println(obj.minCost("aaaa", new int[]{1, 2, 3, 4}));
        System.out.println(obj.minCost("aabaa", new int[]{1, 2, 3, 4, 1}));
        System.out.println(obj.minCost("abaac", new int[]{1, 2, 3, 4, 5}));
        System.out.println(obj.minCost("bbbaaa", new int[]{4, 9, 3, 8, 8, 9}));
    }

    public int minCost(String s, int[] cost) {
        int minCost = 0;
        int n = s.length();
        if (n <= 1) return 0;
        int left = 0, right = 1;
        while (right < n) {
            if (s.charAt(left) == s.charAt(right)) {
                if (cost[left] > cost[right]) {
                    minCost += cost[right];
                    right++;
                } else {
                    minCost += cost[left];
                    left = right;
                    right = left + 1;
                }
            } else {
                left = right;
                right = left + 1;
            }
        }

        return minCost;
    }
}