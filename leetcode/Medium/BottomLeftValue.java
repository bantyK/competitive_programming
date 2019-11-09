package solutions.medium;

import models.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/find-bottom-left-tree-value/
public class BottomLeftValue {
    public static void main(String[] args) {
        BottomLeftValue obj = new BottomLeftValue();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);

        System.out.println(obj.findBottomLeftValue(root));
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int firstValue = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();

                if (i == 0) {
                    firstValue = node.val;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return firstValue;

    }
}
