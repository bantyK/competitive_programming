// 1763 https://leetcode.com/problems/longest-nice-substring/

import java.util.*;

public class LongestNiceSubarray {

    public static void main(String[] args) {
        LongestNiceSubarray obj = new LongestNiceSubarray();
        System.out.println(obj.longestNiceSubstring("AaazbbBB"));
        System.out.println(obj.longestNiceSubstring("YazaAay"));
    }

    public String longestNiceSubstring(String s) {
        if (s == null || s.length() <= 1) return "";

        return helper(s, 0, s.length() - 1);
    }

    private String helper(String s, int start, int end) {
        if (start >= end) return "";

        Set<Character> set = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            set.add(ch);
        }


        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            char lower = Character.toLowerCase(ch);
            char upper = Character.toUpperCase(ch);

            if (set.contains(lower) && !set.contains(upper) || set.contains(upper) && !set.contains(lower)) {
                String left = helper(s, start, i - 1);
                String right = helper(s, i + 1, end);

                if (left.length() == right.length()) {
                    return left;
                } else {
                    return left.length() > right.length() ? left : right;
                }
            }
        }

        return s.substring(start, end + 1);
    }

}
