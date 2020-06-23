import input.TreeNode;

//222 https://leetcode.com/problems/count-complete-tree-nodes/
public class CountCompleteTreeNodes {

    // Optimised solution
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public int leftDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + leftDepth(root.left);
    }

    public int rightDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + rightDepth(root.right);
    }


    // Brute force
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);

        return 1 + left + right;
    }
}
