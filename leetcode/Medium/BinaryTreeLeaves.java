import input.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//366 https://leetcode.com/problems/find-leaves-of-binary-tree/
public class BinaryTreeLeaves {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(new BinaryTreeLeaves().findLeaves1(root));


    }

    //////// using MAP ////////
    private Map<Integer, List<Integer>> heightMap;

    public List<List<Integer>> findLeaves(TreeNode root) {
        heightMap = new HashMap<>();
        getHeight(root);

        List<List<Integer>> res = new ArrayList<>();
        for (int key : heightMap.keySet()) {
            res.add(heightMap.get(key));
        }
        return res;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return -1;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int currentHeight = Math.max(leftHeight, rightHeight) + 1;

        heightMap.putIfAbsent(currentHeight, new ArrayList<>());

        heightMap.get(currentHeight).add(root.val);
        return currentHeight;
    }

    ////////Without MAP////////

    public List<List<Integer>> findLeaves1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    private int helper(List<List<Integer>> res, TreeNode root) {
        if (root == null) return -1;
        int leftLevel = helper(res, root.left);
        int rightLevel = helper(res, root.right);

        int currentLevel = Math.max(leftLevel, rightLevel) + 1;

        if (currentLevel == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(currentLevel).add(root.val);
        root.left = null;
        root.right = null;
        return currentLevel;
    }

}
