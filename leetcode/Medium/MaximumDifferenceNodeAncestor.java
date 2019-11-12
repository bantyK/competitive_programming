package solutions.medium;

import models.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
public class MaximumDifferenceNodeAncestor {
    public static void main(String[] args) {
        MaximumDifferenceNodeAncestor obj = new MaximumDifferenceNodeAncestor();

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);

        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(0);
        root.left.right = new TreeNode(6);

        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);

        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        System.out.println(
                obj.maxAncestorDiff(root)
        );

    }

    int max = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return max;
    }

    private List<TreeNode> helper(TreeNode root) {
        List<TreeNode> temp = new ArrayList<>();
        if (root == null) return temp;

        if (root.left == null && root.right == null) {
            temp.add(root);
            return temp;
        }

        List<TreeNode> left = helper(root.left);
        List<TreeNode> right = helper(root.right);

        for (TreeNode node : left) {
            max = Math.max(max, Math.abs(node.val - root.val));
        }

        for (TreeNode node : right) {
            max = Math.max(max, Math.abs(node.val - root.val));
        }

        temp.add(root);
        temp.addAll(left);
        temp.addAll(right);
        return temp;
    }
}
