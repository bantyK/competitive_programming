import java.util.*;
// 437 https://leetcode.com/problems/path-sum-iii/
class PathSum3 {
    int total = 0;
    Map<Integer, Integer> count;
    int target;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        count = new HashMap<>();
        target = sum;
        count.put(0, 1);
        helper(root, 0);
        return total;
    }

    private void helper(TreeNode root, int currentSum) {
        if (root == null) {
            return;
        }

        currentSum += root.val;

        if (count.containsKey(currentSum - target)) {
            total += count.get(currentSum - target);
        }

        count.put(currentSum, count.getOrDefault(currentSum, 0) + 1);

        helper(root.left, currentSum);
        helper(root.right, currentSum);

        count.put(currentSum, count.get(currentSum) - 1);
    }
}
