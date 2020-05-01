import input.TreeNode;

//https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/532/week-5/3315/
public class ValidSequence {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.left.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(0);
        root.left.right.right = new TreeNode(0);

        root.right.left = new TreeNode(0);

        ValidSequence obj = new ValidSequence();
//        System.out.println(obj.isValidSequence(root, new int[]{0, 1, 0, 1}));
//        System.out.println(obj.isValidSequence(root, new int[]{0, 0, 0}));
//        System.out.println(obj.isValidSequence(root, new int[]{0, 1, 1}));
//        System.out.println(obj.isValidSequence(root, new int[]{0, 1, 1, 0}));
        System.out.println(obj.isValidSequence(root, new int[]{0, 0, 1}));
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {
        return helper(root, arr, 0);
    }

    private boolean helper(TreeNode root, int[] arr, int index) {
        if (root == null) return false;
        if (index == arr.length - 1) {
            return (root.left == null && root.right == null && root.val == arr[index]);
        } else if (root.val != arr[index]) {
            return false;
        }

        boolean left = helper(root.left, arr, index + 1);
        if (left) return true;

        boolean right = helper(root.right, arr, index + 1);
        return right;

    }
}