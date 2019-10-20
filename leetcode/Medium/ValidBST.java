package solutions.medium;

import models.TreeNode;

//https://leetcode.com/problems/validate-binary-search-tree
public class ValidBST {
    public static void main(String[] args) {
        ValidBST obj = new ValidBST();

        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        if (obj.isValidBST(root)) {
            System.out.println("Valid");
        } else {
            System.out.println("Not valid");
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long minValue, long maxValue) {
        if (root == null) return true;

        return root.val > minValue
                && root.val < maxValue
                && isValidBSTHelper(root.left, minValue, root.val)
                && isValidBSTHelper(root.right, root.val, maxValue);
    }
}