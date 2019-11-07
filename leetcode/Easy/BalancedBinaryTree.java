package solutions.easy;

import models.TreeNode;

// https://leetcode.com/problems/balanced-binary-tree/
public class BalancedBinaryTree {
    public static void main(String[] args) {
        BalancedBinaryTree obj = new BalancedBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);

        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);


        System.out.println(obj.isBalanced(root));
    }

    private class BalancedStatusWithHeight {
        int height;
        boolean isBalanced;

        public BalancedStatusWithHeight(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return checkBalanced(root).isBalanced;
    }

    private BalancedStatusWithHeight checkBalanced(TreeNode root) {

        if (root == null) return new BalancedStatusWithHeight(-1, true);

        BalancedStatusWithHeight left = checkBalanced(root.left);

        if (!left.isBalanced) return left;

        BalancedStatusWithHeight right = checkBalanced(root.right);

        if(!right.isBalanced) return right;

        boolean subtreesBalanced = Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;

        return new BalancedStatusWithHeight(height, subtreesBalanced);

    }


    private int height(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

}
