package solutions.medium;

import models.TreeNode;

import java.util.*;

// https://leetcode.com/problems/most-frequent-subtree-sum/

public class MostFrequentSubtreeSum {
    
    public static void main(String[] args) {
        MostFrequentSubtreeSum obj = new MostFrequentSubtreeSum();
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(5);
        root.right = new TreeNode(5);

        for (int i : obj.findFrequentTreeSum(root)) {
            System.out.print(i + " ");
        }
        System.out.println();

    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[]{};

        Map<Integer, Integer> countMap = new TreeMap<>();
        calculateSum(root, countMap);

        PriorityQueue<Integer[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int key : countMap.keySet()) {
            maxHeap.add(new Integer[]{key, countMap.get(key)});
        }

        int maxValue = maxHeap.peek()[1];

        List<Integer[]> res = new ArrayList<>();

        while (!maxHeap.isEmpty() && maxHeap.peek()[1] == maxValue) {
            Integer[] ints = maxHeap.poll();
            res.add(ints);
        }

        int[] result = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i)[0];
        }

        return result;
    }

    private int calculateSum(TreeNode root, Map<Integer, Integer> countMap) {
        if (root == null) return 0;
        int leftSum = calculateSum(root.left, countMap);
        int rightSum = calculateSum(root.right, countMap);

        int total = root.val + leftSum + rightSum;
        countMap.put(total, countMap.getOrDefault(total, 0) + 1);
        return total;
    }
}
