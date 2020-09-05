import java.util.*;

// 1305 https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
public class AllElements2BST {

	// Using stack 
	 public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> res = new ArrayList<>();
        populateStack(stack1, root1);
        populateStack(stack2, root2);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode node1 = stack1.peek();
            TreeNode node2 = stack2.peek();
            if (node1.val > node2.val) {
                TreeNode node = stack2.pop();
                res.add(node.val);
                populateStack(stack2, node.right);
            } else {
                TreeNode node = stack1.pop();
                res.add(node.val);
                populateStack(stack1, node.right);
            }
        }

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            res.add(node.val);
            populateStack(stack1, node.right);
        }

        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            res.add(node.val);
            populateStack(stack2, node);
        }

        return res;
    }


    private void populateStack(Stack<TreeNode> stack, TreeNode root) {
        if (root == null) return;
        stack.push(root);
        populateStack(stack, root.left);
    }


    /**
     * Brute Force
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> values1 = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();

        inorder(root1, values1);
        inorder(root2, values2);

        if (root1 == null) return values2;
        else if (root2 == null) return values1;
        List<Integer> result = new ArrayList<Integer>();

        int index1 = 0, index2 = 0;

        while (index1 < values1.size() && index2 < values2.size()) {
            if (values1.get(index1) < values2.get(index2)) {
                result.add(values1.get(index1));
                index1++;
            } else {
                result.add(values2.get(index2));
                index2++;
            }
        }

        while (index1 < values1.size()) {
            result.add(values1.get(index1));
            index1++;
        }

        while (index2 < values2.size()) {
            result.add(values2.get(index2));
            index2++;
        }

        return result;
    }

    private void inorder(TreeNode root, List<Integer> values) {
        if (root != null) {
            inorder(root.left, values);
            values.add(root.val);
            inorder(root.right, values);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
