import input.TreeNode;

// 1325 https://leetcode.com/problems/delete-leaves-with-a-given-value/
public class DeleteLeavesWithGivenTarget {
    public static void main(String[] args) {
        DeleteLeavesWithGivenTarget obj = new DeleteLeavesWithGivenTarget();

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(5);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);

        TreeNode temp = obj.removeLeafNodes(root, 2);
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if(root.val == target && root.left == null && root.right == null) return null;

        return root;
    }
}
