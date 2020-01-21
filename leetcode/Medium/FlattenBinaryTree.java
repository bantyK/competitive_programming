import input.TreeNode;

// 114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
public class FlattenBinaryTree {
    public static void main(String[] args) {
        FlattenBinaryTree obj = new FlattenBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        obj.flatten(root);
        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    public void flatten(TreeNode root) {
        helper(root);
    }

    public TreeNode helper(TreeNode root) {
        if (root == null) return null;
        if(root.left == null && root.right == null) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);

        root.left = null;
        TreeNode temp = right;
        root.right = left;
        TreeNode node = root;
        while(node.right != null) {
            node = node.right;
        }
        node.right = temp;

        return root;
    }
}
