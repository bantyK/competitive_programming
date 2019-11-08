package solutions.easy;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// https://leetcode.com/problems/convert-bst-to-greater-tree/
public class BSTGreater {
    public static void main(String[] args) {
        BSTGreater obj = new BSTGreater();

        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(25);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(12);

        BinaryTreeUtils.inorderTraversal(obj.convertBST(root));
    }


    public TreeNode convertBST(TreeNode root) {
        TreeNode result = convert(root, 0, 0);
        return result;
    }


    private TreeNode convert(TreeNode root, int initial, int rootVal) {
        if (root == null) return null;

        else {
            convert(root.right, initial, rootVal);
            root.val += initial;
            rootVal = root.val;
            convert(root.left, initial, rootVal);
            return root;
        }
    }
}
