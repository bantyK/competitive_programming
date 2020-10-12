import java.util.*;

//Leetcode# 316 https://leetcode.com/problems/remove-duplicate-letters/
//Leetcode#1081 https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
public class SmallestSubsequence {
    public static void main(String[] args) {
        SmallestSubsequence obj = new SmallestSubsequence();
        System.out.println(obj.smallestSubsequence("bcabc"));
        System.out.println(obj.smallestSubsequence("cbacdcbc"));
    }

    public String smallestSubsequence(String s) {
        char[] arr = s.toCharArray();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for (char c : arr) {
            count[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            count[index]--;

            if (used[index]) continue;

            used[index] = true;

            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] != 0) {
                char pop = stack.pop();
                used[pop - 'a'] = false;
            }

            stack.push(c);
            used[index] = true;
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }

        return builder.toString();
    }
}
