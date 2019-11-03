package solutions.medium;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// https://leetcode.com/problems/binary-tree-pruning/submissions/
public class BinaryTreePruning {
    public static void main(String[] args) {
        BinaryTreePruning obj = new BinaryTreePruning();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.left = new TreeNode(1);

        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(0);

//        BinaryTreeUtils.inorderTraversal(root);
        obj.pruneTree(root);
        BinaryTreeUtils.inorderTraversal(root);

    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;

        if (root.left != null)
            root.left = pruneTree(root.left);

        if (root.right != null)
            root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
