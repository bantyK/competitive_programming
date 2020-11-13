import input.TreeNode;
//1120 https://leetcode.com/problems/maximum-average-subtree/
public class MaximumAverageSubtree {
    private double maxAverage = 0;

    public static void main(String[] args) {
        MaximumAverageSubtree obj = new MaximumAverageSubtree();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);

        System.out.println(obj.maximumAverageSubtree(root));
    }

    public double maximumAverageSubtree(TreeNode root) {
        maxAverage = 0;
        helper(root);
        return maxAverage;
    }

    // {count, average}
    public int[] helper(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int newCount = left[0] + right[0] + 1;
        int newSum = left[1] + right[1] + root.val;

        double average = newSum / (1.0 * newCount);
        maxAverage = Math.max(maxAverage, average);

        return new int[]{newCount, newSum};
    }
}
