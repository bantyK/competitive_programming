package solutions.medium;

import models.TreeNode;

import java.util.*;

// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        VerticalOrderTraversal obj = new VerticalOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> lists = obj.verticalTraversal(root);
        for (List<Integer> val : lists) {
            System.out.println(val);
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
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

    private void populateHorizontalDistanceMap(TreeNode root, int distance, Map<Integer, List<Integer>> map) {
        if (root != null) {
            if (map.get(distance) == null)
                map.put(distance, new ArrayList<>());
            map.get(distance).add(root.val);
            populateHorizontalDistanceMap(root.left, distance - 1, map);
            populateHorizontalDistanceMap(root.right, distance + 1, map);
        }
    }
}
