package solutions.medium;

import java.util.*;

//1249 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class ValidParanthesis {
    public static void main(String[] args) {
        ValidParanthesis obj = new ValidParanthesis();

        String res = obj.minRemoveToMakeValid("lee(t(c)o)de)");

        System.out.println(res);
    }

    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = new char[s.length()];
        Arrays.fill(chars,' ');
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    int index = stack.pop();
                    chars[index] = '(';
                    chars[i] = ')';
                }
            } else {
                chars[i] = s.charAt(i);
            }
        }

        return new String(chars).replace(" ", "");

    }
}
