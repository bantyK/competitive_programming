//5145: https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
public class EvenValuedGrandparentSum {
	public int sumEvenGrandparent(TreeNode root) {
        	if (root == null) return 0;
	        int totalSum = 0;
        	if (root.val % 2 == 0) {
	            totalSum = getSum(root.left) + getSum(root.right);
        	}

	        return totalSum + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
    	}

    private int getSum(TreeNode left) {
        if (left == null) return 0;
        int sum = 0;
        sum += left.left != null ? left.left.val : 0;
        sum += left.right != null ? left.right.val : 0;
        return sum;
    }
}
