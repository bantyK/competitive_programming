package solutions.medium;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

import java.util.*;

public class BinaryTreeTopView {
    public static void main(String[] args) {
        BinaryTreeTopView obj = new BinaryTreeTopView();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(3);
        root.right.right.left.right = new TreeNode(4);

        printTopView(root);
    }

    public static void printTopView(TreeNode root) {
        List<Integer> levelOrderTraversal = getLevelOrderTraversal(root);
        List<List<Integer>> verticalOrderTraversal = verticalTraversal(root);

        for(List<Integer> nodes : verticalOrderTraversal) {
            if(nodes.size() == 1) {
                System.out.print(nodes.get(0));
            } else {
                int minIndexValue = Integer.MAX_VALUE;
                for(int i : nodes) {
                    if(levelOrderTraversal.indexOf(i) < minIndexValue) {
                        minIndexValue = i;
                    }
                }
                System.out.print(minIndexValue);
            }
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> horizontalDistanceMap = new TreeMap<>();
        populateHorizontalDistanceMap(root, 0, horizontalDistanceMap);

        for (int distance : horizontalDistanceMap.keySet()) {
            List<Integer> nodes = horizontalDistanceMap.get(distance);
            Collections.sort(nodes);
            res.add(nodes);
        }

        return res;
    }

    private static void populateHorizontalDistanceMap(TreeNode root, int distance, Map<Integer, List<Integer>> map) {
        if (root != null) {
            if (map.get(distance) == null)
                map.put(distance, new ArrayList<>());
            map.get(distance).add(root.val);
            populateHorizontalDistanceMap(root.left, distance - 1, map);
            populateHorizontalDistanceMap(root.right, distance + 1, map);
        }
    }

    public static List<Integer> getLevelOrderTraversal(TreeNode root) {
        List<Integer> levelOrderTraversal = new ArrayList<>();
        if (root == null) return null;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            levelOrderTraversal.add(current.val);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return levelOrderTraversal;
    }
}
