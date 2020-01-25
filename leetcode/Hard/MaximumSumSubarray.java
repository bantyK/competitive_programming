package sliding;

import java.util.*;

// 76 https://leetcode.com/problems/minimum-window-substring/
public class MaximumSumSubarray {


    public static void main(String[] args) {
        MaximumSumSubarray obj = new MaximumSumSubarray();
        final String res = obj.minWindow("XYZAAGHBCAABC", "AABC");
        System.out.println(res);
    }

    /**
     * Sliding Window
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length() || s.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int left = 0;
        int minLeft = 0;
        int minLen = s.length() + 1;
        int count = 0;

        for (int right = 0; right < s.length(); right++) {
            final char rightChar = s.charAt(right);

            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) >= 0) {
                    count++;
                }

                while (count == t.length()) {
                    if (right - left + 1 < minLen) {
                        minLeft = left;
                        minLen = right - left + 1;
                    }
                    final char leftChar = s.charAt(left);

                    if (map.containsKey(leftChar)) {
                        map.put(leftChar, map.get(leftChar) + 1);
                        if (map.get(leftChar) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        if (minLen > s.length()) {
            return "";
        }

        return s.substring(minLeft, minLeft + minLen);
    }

    /**
     * Brute force
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        Map<Character, Integer> chars = new HashMap<>();
        for (char c : t.toCharArray()) {
            int prevCount = chars.getOrDefault(c, 0);
            chars.put(c, prevCount + 1);
        }

        int minLength = Integer.MAX_VALUE;
        int currentLength;
        int start = -1, end = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (containsAllChars(s, i, j, new HashMap<>(chars))) {
                    currentLength = j - i + 1;
                    if (currentLength < minLength) {
                        minLength = currentLength;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        if (start < 0) return "";
        return s.substring(start, end + 1);
    }

    private boolean containsAllChars(String s, int start, int end, HashMap<Character, Integer> charMap) {
        int counter = charMap.size();
        for (int i = start; i <= end; i++) {
            final char c = s.charAt(i);
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) - 1);
                if (charMap.get(c) == 0) {
                    charMap.remove(c);
                    counter--;
                }
            }
        }

        return counter == 0;
    }


}
