package src;

import java.util.Stack;

//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class MinimumAddParentheses {
    public static void main(String[] args) {
        MinimumAddParentheses obj = new MinimumAddParentheses();
        System.out.println(
                obj.minAddToMakeValid("(((()))(())(())((()") == 3
        );
    }

    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        int minCharRequired = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty()) stack.pop();
                else minCharRequired++;
            }
        }

        return stack.size() + minCharRequired;
    }
}
