import java.util.*;

// 129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class RootToLeafPathSum {

    /**
     * Efficient accepted answer
     *
     * @param root
     * @return
     */
    public int sumNumbersEfficient(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int sum) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return sum * 10 + root.val;

        return helper(root.left, sum * 10 + root.val) + helper(root.right, sum * 10 + root.val);
    }

    /**
     * Backtracking slow
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        List<String> results = new ArrayList<>();
        allRootToLeafPaths(root, new StringBuilder(), results);
        return results.stream().mapToInt(Integer::parseInt).sum();
    }

    private void allRootToLeafPaths(TreeNode root, StringBuilder builder, List<String> results) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            results.add(builder.toString() + root.val);
            return;
        }

        builder.append(root.val);
        allRootToLeafPaths(root.left, builder, results);
        if (root.right != null) {
            allRootToLeafPaths(root.right, builder, results);
        }
        builder.deleteCharAt(builder.length() - 1);

    }

    private static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int data) {
            this.val = data;
        }
    }
}
