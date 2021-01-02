import java.util.*;

import input.TreeNode;

//1028 https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
public class RecoverBinaryTree {
    public static void main(String[] args) {
        RecoverBinaryTree obj = new RecoverBinaryTree();
        // TreeNode res = obj.recoverFromPreorder("5-4--4");
        TreeNode res = obj.recoverFromPreorder("3");
        System.out.println(res);
    }

    TreeNode curr;

    public TreeNode recoverFromPreorder(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int currentNum = 0;
        int level = 0;
        curr = null;
        TreeNode root = null;
        Stack<StackNode> stack = new Stack<>(); // {value, level}
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                currentNum = (currentNum * 10) + (ch - '0');
            } else if (ch == '-') {
                if (currentNum > 0) {
                    TreeNode node = new TreeNode(currentNum);
                    if (stack.isEmpty()) {
                        root = node;
                        curr = node;
                        stack.push(new StackNode(node, level));
                    } else {
                        addNode(level, node, stack);
                    }
                    currentNum = 0;
                    level = 1;
                } else {
                    level++;
                }
            }
        }

        // this is for the last number, the for loops exits before processing the last
        // number
        if (stack.isEmpty()) {
            return new TreeNode(currentNum);
        } else {
            addNode(level, new TreeNode(currentNum), stack);
        }
        return root;

    }

    // Every node will ask the already added node whether that node could be
    // the parent of this new node
    // The already added node could be the parent of new node, if its level is less
    // than the new node

    // we will store this info into the stack
    // the top of the stack gives the node which was just added into the tree

    // If the new nodes level is less than the already added node, we will pop all
    // such
    // nodes whose level is more than current nodes level, because we dont need to
    // track
    // those nodes anymore because it is a preorder traversal.

    private void addNode(int level, TreeNode node, Stack<StackNode> stack) {
        if (stack.peek().level < level) {
            if (curr.left == null) {
                curr.left = node;
                curr = curr.left;
            } else {
                curr.right = node;
                curr = curr.right;
            }

            stack.push(new StackNode(node, level));
        } else {
            while (stack.peek().level >= level) {
                // discard all the nodes whose level is greater than the current levels node
                // we wont need these nodes anymore because the given string is preorder
                // traversal
                // think about this for a while.
                stack.pop();
            }

            curr = stack.peek().node;

            if (curr.left == null) {
                curr.left = node;
                curr = curr.left;
            } else {
                curr.right = node;
                curr = curr.right;
            }

            stack.push(new StackNode(node, level));
        }
    }

    private class StackNode {
        TreeNode node;
        int level;

        public StackNode(TreeNode tn, int l) {
            node = tn;
            level = l;
        }
    }
}