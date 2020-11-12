import input.TreeNode;

import java.util.*;

//1609 https://leetcode.com/problems/even-odd-tree/
public class OddEvenTree {
    public static void main(String[] args) {
        OddEvenTree obj = new OddEvenTree();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(7);

        System.out.println(obj.isEvenOddTreeOptimised(root));
    }


    public boolean isEvenOddTreeOptimised(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        boolean evenLevel = true;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int prevVal = evenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (evenLevel) {
                    if (curr.val % 2 == 0 || curr.val <= prevVal) return false;
                } else {
                    if (curr.val % 2 == 1 || curr.val >= prevVal) return false;
                }

                prevVal = curr.val;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            evenLevel = !evenLevel;
        }

        return true;
    }

    public boolean isEvenOddTree(TreeNode root) {
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                nodes.add(curr.val);

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            System.out.println(nodes);
            if (level % 2 == 0) {
                if (!validEvenLevelRules(nodes)) {
                    return false;
                }
            } else {
                if (!validOddLevelRules(nodes)) {
                    return false;
                }
            }
            ++level;
        }
        return true;
    }

    // strictly increasing and all odd values
    private boolean validEvenLevelRules(List<Integer> values) {
        int prev = Integer.MIN_VALUE;
        for (int val : values) {
            if (val % 2 == 0) return false;
            if (val <= prev) return false;
            prev = val;
        }

        return true;
    }

    // strictly decreasing and all even values
    private boolean validOddLevelRules(List<Integer> values) {
        System.out.println(values);
        int prev = Integer.MAX_VALUE;
        for (int val : values) {
            if (val % 2 == 1) return false;
            if (val >= prev) return false;
            prev = val;
        }

        return true;
    }
}
