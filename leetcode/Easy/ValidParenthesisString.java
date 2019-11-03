package solutions.easy;

import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParenthesisString {
    public static void main(String[] args) {
        ValidParenthesisString obj = new ValidParenthesisString();
        System.out.println(obj.isValid("([)]"));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        if (s.length() % 2 == 1) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char top;
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                else {
                    top = stack.pop();

                    if (c == ')') {
                        if (top != '(') {
                            return false;
                        }
                    } else if (c == '}') {
                        if (top != '{') {
                            return false;
                        }
                    } else {
                        if (top != '[') {
                            return false;
                        }
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
