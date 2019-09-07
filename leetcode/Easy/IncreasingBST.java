package solutions;

import models.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/increasing-order-search-tree/
public class IncreasingBST {

    public static void main(String[] args) {
        IncreasingBST obj = new IncreasingBST();
        TreeNode root = null;
        root = obj.addNodeToTree(root, new TreeNode(5));
        root = obj.addNodeToTree(root, new TreeNode(3));
        root = obj.addNodeToTree(root, new TreeNode(6));
        root = obj.addNodeToTree(root, new TreeNode(2));
        root = obj.addNodeToTree(root, new TreeNode(4));
        root = obj.addNodeToTree(root, new TreeNode(1));
        root = obj.addNodeToTree(root, new TreeNode(8));
        root = obj.addNodeToTree(root, new TreeNode(7));
        root = obj.addNodeToTree(root, new TreeNode(9));

        TreeNode resultNode = obj.increasingBST(root);

        System.out.println(resultNode);
    }

    private TreeNode addNodeToTree(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        } else if (node.value > root.value) {
            //add to right
            root.right = addNodeToTree(root.right, node);
        } else {
            // add to left
            root.left = addNodeToTree(root.left, node);
        }
        return root;
    }

    public TreeNode increasingBST(TreeNode root) {
        List<Integer> increasingNodeValues = new ArrayList<>();
        inorderTraversal(root, increasingNodeValues);

        TreeNode rightRoot = null;
        for (Integer val : increasingNodeValues)
            rightRoot = addRightNode(rightRoot, val);

        return rightRoot;
    }

    private TreeNode addRightNode(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            root.right = addRightNode(root.right, value);
        }
        return root;
    }

    private void inorderTraversal(TreeNode root, List<Integer> nodeValues) {
        if (root != null) {
            inorderTraversal(root.left, nodeValues);
            nodeValues.add(root.value);
            inorderTraversal(root.right, nodeValues);
        }
    }

}
