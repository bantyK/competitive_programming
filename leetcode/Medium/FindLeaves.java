import input.TreeNode;

import java.util.*;

// 366 https://leetcode.com/problems/find-leaves-of-binary-tree/
public class FindLeaves {

    public static void main(String[] args) {
        FindLeaves obj = new FindLeaves();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        System.out.println(obj.findLeaves(root));
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = getLeaves(root, leaves);
            res.add(leaves);
        }

        return res;
    }

    private TreeNode getLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return null;

        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return null;
        }
        root.left = getLeaves(root.left, leaves);
        root.right = getLeaves(root.right, leaves);
        return root;
    }
}