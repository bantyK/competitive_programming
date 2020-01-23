import input.TreeNode;

import java.util.Stack;

//536 https://leetcode.com/problems/construct-binary-tree-from-string/
public class ConstructBinaryTree {
    private static int i = 0;

    public TreeNode str2tree(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private TreeNode helper(String s, int startIndex, int endIndex) {
        if (startIndex > endIndex) return null;
        if (startIndex == endIndex) {
            if (Character.isDigit(s.charAt(startIndex))) {
                return new TreeNode(Character.getNumericValue(s.charAt(startIndex)));
            } else {
                return null;
            }
        }
        int firstOpenIndex = s.indexOf('(', startIndex);

        String substring;
        int rootData;
        TreeNode root;
        if (firstOpenIndex == -1 || firstOpenIndex > endIndex) {
            substring = s.substring(startIndex, endIndex + 1);
            rootData = Integer.parseInt(substring);
            root = new TreeNode(rootData);
            return root;
        } else {
            substring = s.substring(startIndex, firstOpenIndex);
            rootData = Integer.parseInt(substring);
            root = new TreeNode(rootData);
        }

        int leftTreeStartIndex = firstOpenIndex + 1;
        int leftTreeEndIndex = getLeftTreeEndIndex(s, firstOpenIndex);
        int rightTreeStartIndex = leftTreeEndIndex + 2;
        int rightTreeEndIndex = endIndex;

        root.left = helper(s, leftTreeStartIndex, leftTreeEndIndex - 1);
        root.right = helper(s, rightTreeStartIndex, rightTreeEndIndex - 1);
        return root;

    }

    private int getLeftTreeEndIndex(String s, int startIndex) {
        Stack<Character> stack = new Stack<>();

        for (int i = startIndex; i < s.length(); i++) {
            final char currentChar = s.charAt(i);
            if (currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    return i;
                }
            }
        }
        return s.length() - 1;
    }

}