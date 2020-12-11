import input.TreeNode;

import java.util.*;

//958 https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class CheckBinaryTreeCompleteness {

    public static void main(String[] args) {

        CheckBinaryTreeCompleteness obj = new CheckBinaryTreeCompleteness();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);


        System.out.println(obj.isCompleteTreeOptimised(root));
    }

    // For a full binary tree, all the null values should be towards the end of tht queue
    // there should not be any non null value after the null value
    public boolean isCompleteTreeOptimised(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                // we found a null, set the flag
                end = true;
            } else {
                if (end) {
                    // we reached here, meaning we have a non null node
                    // and this flag is also set, meaning we have already found null before
                    // then this can't be a full binary tree
                    return false;
                }
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return true;
    }

    // My approach
    public boolean isCompleteTree(TreeNode root) {
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 0));
        int maxLevel = height(root);
        int currentLevel = 1;
        while (!queue.isEmpty()) {
            int parentIndex = 0;
            int childIndex = 0;
            int size = queue.size();
            int countNode = 0;
            for (int i = 0; i < size; i++) {
                QueueNode curr = queue.poll();
                countNode++;
                if (curr.index != parentIndex) return false;
                parentIndex++;

                if (curr.node.left != null) {
                    queue.offer(new QueueNode(curr.node.left, childIndex));
                }
                childIndex++;

                if (curr.node.right != null) {
                    queue.offer(new QueueNode(curr.node.right, childIndex));
                }
                childIndex++;
            }

            if (currentLevel != maxLevel && countNode != (int) Math.pow(2, currentLevel - 1)) {
                return false;
            }
            currentLevel++;
        }

        return true;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + height(root.left);
    }


    private static class QueueNode {
        TreeNode node;
        int index;

        public QueueNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}