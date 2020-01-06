import java.util.*;

// 105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class BuildTree {
    public static void main(String[] args) {
        BuildTree obj = new BuildTree();
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return constructTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int[] inorder, int preS, int preE, int inS, int inE) {
        if (preS > preE) return null;

        int rootVal = preorder[preS];

        int rootIndexInInorder = 0;
        for (int i = inS; i <= inE; i++) {
            if (inorder[i] == rootVal) {
                rootIndexInInorder = i;
                break;
            }
        }

        TreeNode rootNode = new TreeNode(rootVal);

        int leftIns = inS;
        int leftInE = rootIndexInInorder - 1;
        int rightInS = rootIndexInInorder + 1;
        int rightInE = inE;

        int leftPreS = preS + 1;
        int leftPreE = leftInE - leftIns + leftPreS;
        int rightPreS = leftPreE + 1;
        int rightPreE = preE;

        rootNode.left = constructTree(preorder, inorder, leftPreS, leftPreE, leftIns, leftInE);
        rootNode.right = constructTree(preorder, inorder, rightPreS, rightPreE, rightInS, rightInE);

        return rootNode;
    }


}
