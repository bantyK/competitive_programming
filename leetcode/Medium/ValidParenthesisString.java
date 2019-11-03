package solutions.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParenthesisString {
    public static void main(String[] args) {
        ValidParenthesisString obj = new ValidParenthesisString();
        //"(())((())()()(*)(*()(())())())()()((()())((()))(*"
        System.out.println(obj.checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
    }

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) return true;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack1.push(i);
            } else if (c == '*') {
                stack2.push(i);
            } else {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                } else if (!stack2.isEmpty()) {
                    stack2.pop();
                } else {
                    return false;
                }
            }
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            Integer value = stack2.pop();
            if (stack1.pop() > value) {
                return false;
            }
        }

        return stack1.isEmpty();

    }
}
