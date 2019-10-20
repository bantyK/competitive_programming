package solutions.medium;

import models.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/cousins-in-binary-tree/
public class BinaryTreeCousins {
    public static void main(String[] args) {
        BinaryTreeCousins obj = new BinaryTreeCousins();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        if (obj.isCousins(root, 4, 5)) {
            System.out.println("Cousins");
        } else {
            System.out.println("Not cousins");
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        int parentX = -1;
        int parentY = -1;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            list.clear();
            for (int i = 1; i <= n; i++) {
                TreeNode temp = queue.poll();

                list.add(temp.val);

                if (temp.left != null) {
                    if (temp.left.val == x) {
                        parentX = temp.val;
                    }

                    if (temp.left.val == y) {
                        parentY = temp.val;
                    }

                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    if (temp.right.val == x) {
                        parentX = temp.val;
                    }

                    if (temp.right.val == y) {
                        parentY = temp.val;
                    }
                    queue.offer(temp.right);
                }
            }

            if(list.contains(x) && list.contains(y)) {
                return parentX != parentY;
            }

        }
        return false;
    }
}
