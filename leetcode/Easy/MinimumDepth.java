package solutions.easy;

import models.TreeNode;

// 111. https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class MinimumDepth {
    public static void main(String[] args) {
        MinimumDepth obj = new MinimumDepth();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(2);

        int i = obj.minDepth(root);
        System.out.println(i);
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        else if (root.left == null && root.right == null) {
            return 1;
        }

        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);

        if (leftHeight != 0 && rightHeight != 0) {
            return Math.min(leftHeight, rightHeight) + 1;
        } else if (leftHeight != 0) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
}
