package solutions.medium;

import models.TreeNode;
import solutions.util.ArrayUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-right-side-view/submissions/
public class BinaryTreeRightView {
    public static void main(String[] args) {
        BinaryTreeRightView obj = new BinaryTreeRightView();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        List<Integer> integers = obj.rightSideView(root);
        ArrayUtils.printArrayList(integers);
    }

    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 1; i <= n; i++) {
                TreeNode temp = queue.poll();

                if (i == n) {
                    result.add(temp.val);
                }

                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }

        return result;
    }
}
