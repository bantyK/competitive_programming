package solutions.medium;

import java.util.*;

import models.TreeNode;

//1110: https://leetcode.com/problems/delete-nodes-and-return-forest
public class TreeForest {
    List<TreeNode> results = new ArrayList<>();

    public static void main(String[] args) {
        TreeForest obj = new TreeForest();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<TreeNode> results = obj.delNodes(root, new int[]{3, 5});
        System.out.println(results.size());

    }


    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) set.add(i);

        List<TreeNode> results = new ArrayList<>();
        if (!set.contains(root.val)) results.add(root);
        helper(root, set, results);
        return results;
    }

    private TreeNode helper(TreeNode root, Set<Integer> set, List<TreeNode> results) {
        if (root == null) {
            return null;
        }

        root.left = helper(root.left, set, results);
        root.right = helper(root.right, set, results);

        if (set.contains(root.val)) {
            if (root.left != null) results.add(root.left);
            if (root.right != null) results.add(root.right);
            return null;
        }
        return root;
    }
}
