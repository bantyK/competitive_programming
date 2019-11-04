package solutions.medium;

import models.TreeNode;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class ConstructTreeFromInorderPostorder {
    public static void main(String[] args) {
        ConstructTreeFromInorderPostorder obj = new ConstructTreeFromInorderPostorder();
        int[] inorder = new int[]{4, 2, 5, 1, 3, 7};
        int[] postorder = new int[]{4, 5, 2, 7, 3, 1};
        TreeNode root = obj.buildTree(inorder, postorder); // tree
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        return buildTreeHelper(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int inS, int inE, int poS, int poE) {
        if (inS > inE) return null;

        int rootData = postorder[poE];

        int rootPos = -1;

        for (int i = inS; i <= inE; i++) {
            if (inorder[i] == rootData) {
                rootPos = i;
                break;
            }
        }

        if (rootPos == -1) {
            return null;
        }

        TreeNode root = new TreeNode(rootData);

        int leftInS = inS;
        int leftInE = rootPos - 1;
        int rightInS = rootPos + 1;
        int rightInE = inE;

        int leftPoS = poS;
        int leftPoE = leftInE - leftInS + leftPoS;
        int rightPoS = leftPoE + 1;
        int rightPoE = poE - 1;

        root.left = buildTreeHelper(inorder, postorder, leftInS, leftInE, leftPoS, leftPoE);
        root.right = buildTreeHelper(inorder, postorder, rightInS, rightInE, rightPoS, rightPoE);

        return root;
    }
}
