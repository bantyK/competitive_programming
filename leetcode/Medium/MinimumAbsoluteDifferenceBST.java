//530 https://leetcode.com/problems/minimum-absolute-difference-in-bst/
public class MinimumAbsoluteDifference {
	Integer prev = null;
    int min = Integer.MAX_VALUE;

    
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return min;
        
        getMinimumDifference(root.left);
        
        if(prev != null) {
            min = Math.min(min, root.val - prev);
        }
        
        prev = root.val;
        
        getMinimumDifference(root.right);
        
        return min;
    }
}