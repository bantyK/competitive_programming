import input.TreeNode;

//543 https://leetcode.com/problems/diameter-of-binary-tree/
public class TreeDiameter {
    public static void main(String[] args) {
        TreeDiameter obj = new TreeDiameter();

        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(4);
//        root.left.left = new TreeNode(3);
//
//        root.left.right = new TreeNode(5);
//        root.left.right.right = new TreeNode(6);
//        root.left.right.right.right = new TreeNode(7);

        System.out.println(obj.diameterOfBinaryTree(root));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return helper(root)[0];
    }

    // return {height, diameter}
    public int[] helper(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int rightDiameter = right[0];
        int leftDiameter = left[0];
        int leftHeight = left[1];
        int rightHeight = right[1];

        int height = 1 + Math.max(leftHeight, rightHeight);
        int diameter = Math.max(Math.max(rightDiameter, leftDiameter), leftHeight + rightHeight);

        return new int[]{diameter, height};

    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        else return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
