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
        recurse(root);
    }

    private TreeNode[] recurse(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return new TreeNode[]{root, root};

        TreeNode[] left = recurse(root.left);
        TreeNode[] right = recurse(root.right);

        root.left = null;
        if (left != null) {
            root.right = left[0];
            if(right != null) {
                left[1].right = right[0];

                return new TreeNode[]{root, right[1]};
            }

            return new TreeNode[]{root, left[1]};
        } else {
            if(right == null) {
                return new TreeNode[]{root, root};
            } else {
                root.right = right[0];
                return new TreeNode[]{root, right[1]};
            }
        }
    }
}
