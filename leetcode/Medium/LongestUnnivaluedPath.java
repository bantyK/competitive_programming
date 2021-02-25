import input.TreeNode;

// 687 https://leetcode.com/problems/longest-univalue-path/
public class LongestUnnivaluedPath {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(5);

        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(4);
        root.left.left.left.left.left = new TreeNode(4);
        root.left.left.left.left.right = new TreeNode(4);

        System.out.println(new LongestUnnivaluedPath().longestUnivaluePath(root));
    }

    // Optimised //
    private int max;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        max = 0;
        pathHelper(root);
        return max;
    }

    private int pathHelper(TreeNode root) {
        if (root == null) return 0;

        int left = pathHelper(root.left);
        int right = pathHelper(root.right);

        left = root.left != null && root.val == root.left.val ? 1 + left : 0;
        right = root.right != null && root.val == root.right.val ? 1 + right : 0;

        max = Math.max(left + right, max);
        return Math.max(left, right);
    }


    // Brute Force //
    int rootMax;
    int globalMax;
    public int longestUnivaluePath2(TreeNode root) {
        if (root == null) return 0;
        globalMax = 0;
        helper(root);
        return globalMax;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        rootMax = 0;

        longestPath(root, root.val);
        globalMax = Math.max(globalMax, rootMax);

        rootMax = 0;

        helper(root.left);
        helper(root.right);

    }


    private int longestPath(TreeNode root, int val) {
        if (root == null || root.val != val) return 0;

        int left = longestPath(root.left, val);
        int right = longestPath(root.right, val);

        rootMax = Math.max(left + right, rootMax);
        return 1 + Math.max(left, right);
    }
}