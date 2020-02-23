package sliding;

import java.util.*;

//340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
public class AtmostKDistinctChars {
    public static void main(String[] args) {
        AtmostKDistinctChars obj = new AtmostKDistinctChars();
        System.out.println(obj.lengthOfLongestSubstringKDistinct("eceba", 3));
        System.out.println(obj.lengthOfLongestSubstringKDistinct("kcebaaaaaaaa", 4));
        System.out.println(obj.lengthOfLongestSubstringKDistinct("kb", 10));
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int right;

        Map<Character, Integer> countMap = new HashMap<>();
        int maxLen = 0;

        if (s == null || s.length() == 0) {
            return maxLen;
        }

        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);


            while (countMap.size() > k) {
                char leftChar = s.charAt(left++);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                if (countMap.get(leftChar) == 0) {
                    countMap.remove(leftChar);
                }
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
