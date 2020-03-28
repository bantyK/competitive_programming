import input.TreeNode;
import input.Util;

import java.util.ArrayList;
import java.util.List;

//1382 https://leetcode.com/problems/balance-a-binary-search-tree/
public class BalancedBinarySearchTree {
    public static void main(String[] args) {
        BalancedBinarySearchTree obj = new BalancedBinarySearchTree();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        TreeNode result = obj.balanceBST(root);
        Util.printTree(result);
    }

    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        List<Integer> nodeValues = new ArrayList<>();

        inorder(root, nodeValues);
        return constructBalancedTree(nodeValues, 0, nodeValues.size() - 1);
    }

    private TreeNode constructBalancedTree(List<Integer> values, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = constructBalancedTree(values, start, mid - 1);
        root.right = constructBalancedTree(values, mid + 1, end);
        return root;
    }

    private void inorder(TreeNode root, List<Integer> nodeValues) {
        if (root == null) return;
        inorder(root.left, nodeValues);
        nodeValues.add(root.val);
        inorder(root.right, nodeValues);
    }
}
