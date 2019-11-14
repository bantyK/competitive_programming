package solutions.easy;

import java.util.*;

// 1047: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

public class RemoveAdjacentCharacters {
    public static void main(String[] args) {
        RemoveAdjacentCharacters obj = new RemoveAdjacentCharacters();
        System.out.println(
                obj.removeDuplicatesWithStack("ibfjcaffccadidiaidchakchchcahabhibdcejkdkfbaeeaecdjhajbkfebebfea")
        );
    }

    public String removeDuplicates(String s) {

        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;

        int left = 0;
        int right = left + 1;
        char[] chars = s.toCharArray();

        while (right < s.length()) {
            if (chars[left] == chars[right]) {
                chars[left] = ' ';
                chars[right] = ' ';

                while (left > 0 && chars[left] == ' ') left--;
            } else {
                left = right;
            }
            right++;
        }

        return new String(chars).replace(" ", "");
    }

    // little bit slower earlier approach
    public String removeDuplicatesWithStack(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        char[] chars = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            chars[i] = stack.get(i);
        }

        return new String(chars);
    }
}
