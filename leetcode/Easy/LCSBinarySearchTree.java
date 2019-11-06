package solutions.medium;

import models.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LCSBinarySearchTree {
    public static void main(String[] args) {
        LCSBinarySearchTree obj = new LCSBinarySearchTree();
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        root.left = p;
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        TreeNode q = new TreeNode(4);
        root.left.right.right = q;

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(
                obj.lowestCommonAncestor(root, p, q).val
        );

    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

}

