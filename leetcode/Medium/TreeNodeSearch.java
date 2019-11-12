package solutions.medium;

import models.TreeNode;
import solutions.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

// Previous year google interview question
public class TreeNodeSearch {
    public static void main(String[] args) {
        TreeNodeSearch obj = new TreeNodeSearch();

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(15);
        root.left.left.right = new TreeNode(14);
        root.left.right.left = new TreeNode(13);
        root.left.right.right = new TreeNode(13);

        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(10);

        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(8);


        List<Integer> integers = obj.pathInZigZagTree(root, 25);

        ArrayUtils.printArrayList(integers);
    }

    public List<Integer> pathToNode(TreeNode root, int target) {
        List<Integer> result = new ArrayList<>();

        int level = 0;

        int temp = target;
        while (temp > 1) {
            temp = temp / 2;
            level += 1;
        }

        int numNodelsInLevel = (int) Math.pow(2, level);

        int firstNodeValue = (int) Math.pow(2, level);

        nodes(root, target, numNodelsInLevel, firstNodeValue, 2, result);

        return result;
    }

    private void nodes(TreeNode root, int target, int totalNodes, int firstNode, int divisor, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);

        if (root.val == target) {
            return;
        }

        int partition = firstNode + (totalNodes / divisor) - 1;

        if (partition >= target) {
            nodes(root.left, target, totalNodes, firstNode, divisor * 2, result);
        } else {
            nodes(root.right, target, totalNodes, partition + 1, divisor * 2, result);
        }
    }

    public List<Integer> pathInZigZagTree(TreeNode root, int target) {
        List<Integer> result = new ArrayList<>();

        int level = 0;

        int temp = target;
        while (temp > 1) {
            temp = temp / 2;
            level += 1;
        }

        int firstNodeValue = (int) (Math.pow(2, level));
        int divisor = 2;
        int totalNodesInLevel = firstNodeValue;
        boolean isEvenLevel = level % 2 == 0;

        helper(root, target, totalNodesInLevel, isEvenLevel, firstNodeValue, 2, result);

        return result;
    }

    private void helper(TreeNode root, int target, int totalNodes, boolean isEvenLevel, int firstNodeVal, int divisor, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);

        if (target == root.val) {
            return;
        }

        int partition = firstNodeVal + (totalNodes / divisor) - 1;

        if (isEvenLevel) {
            if (partition >= target) {
                helper(root.left, target, totalNodes, isEvenLevel, firstNodeVal, divisor * 2, result);
            } else {
                helper(root.right, target, totalNodes, isEvenLevel, partition + 1, divisor * 2, result);
            }
        } else {
            if (partition < target) {
                helper(root.left, target, totalNodes, isEvenLevel, partition + 1, divisor * 2, result);
            } else {
                helper(root.right, target, totalNodes, isEvenLevel, firstNodeVal, divisor * 2, result);
            }
        }
    }
}
