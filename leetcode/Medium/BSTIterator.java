import input.TreeNode;

import java.util.*;

// 173 https://leetcode.com/problems/binary-search-tree-iterator/
class BSTIterator {
    
    Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        
        TreeNode node = root;
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode right = node.right;
        while(right != null) {
            stack.push(right);
            right = right.left;
        }
        
        return node.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
