import input.TreeNode;

import java.util.*;

//545 https://leetcode.com/problems/boundary-of-binary-tree/
public class BinaryTreeBoundary {
    public static void main(String[] args) {
        BinaryTreeBoundary obj = new BinaryTreeBoundary();
        TreeNode root = new TreeNode(37);
        root.left = new TreeNode(-34);
        root.right = new TreeNode(-48);

        root.left.right = new TreeNode(-100);

        root.right.left = new TreeNode(-100);
        root.right.right = new TreeNode(48);

        root.right.right.left = new TreeNode(-54);
        root.right.right.left.left = new TreeNode(-71);
        root.right.right.left.right = new TreeNode(-22);


        System.out.println(obj.boundaryOfBinaryTree(root));
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        if (root == null) return Collections.emptyList();

        List<Integer> nodes = new ArrayList<>();

        nodes.add(root.val);

        getLeftNodesExceptRoot(root.left, nodes);

        getLeaves(root, nodes);

        getRightNodesExceptRoot(root.right, nodes);

        return nodes;

    }

    private void getLeaves(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }

        getLeaves(root.left, nodes);
        getLeaves(root.right, nodes);


    }

    private void getLeftNodesExceptRoot(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            nodes.add(root.val);
            getLeftNodesExceptRoot(root.left, nodes);
        } else if (root.right != null) {
            nodes.add(root.val);
            getLeftNodesExceptRoot(root.right, nodes);
        }
    }

    private void getRightNodesExceptRoot(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }

        if (root.right != null) {
            getRightNodesExceptRoot(root.right, nodes);
            nodes.add(root.val);
        } else if (root.left != null) {
            getRightNodesExceptRoot(root.left, nodes);
            nodes.add(root.val);
        }
    }

}
