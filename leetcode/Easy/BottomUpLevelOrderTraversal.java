package solutions.easy;

import models.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
public class BottomUpLevelOrderTraversal {
    public static void main(String[] args) {
        BottomUpLevelOrderTraversal obj = new BottomUpLevelOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        for(List<Integer> l : obj.levelOrderBottom(root)) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return null;
        queue.offer(root);
        List<Integer> list;
        while (!queue.isEmpty()) {
            int n = queue.size();
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                list.add(current.val);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            res.add(0, list);
        }

        return res;
    }
}
