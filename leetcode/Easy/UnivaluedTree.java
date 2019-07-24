package com.company.leet;

public class UnivaluedTree {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        return isUnivalUtil(root, root.val);
    }

    public boolean isUnivalUtil(TreeNode root, int value) {
        if (root == null) return true;
        if (root.val == value) {
            return isUnivalUtil(root.left, value) && isUnivalUtil(root.right, value);
        } else {
            return false;
        }
    }
}
