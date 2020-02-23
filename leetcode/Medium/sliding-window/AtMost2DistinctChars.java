package sliding;

import java.util.*;

//159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class AtMost2DistinctChars {
    public static void main(String[] args) {
        AtMost2DistinctChars obj = new AtMost2DistinctChars();
    }


    /**
     * @param s
     * @return the length of substring with atmost 2 distinct chars. (1 or 2)
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int maxLen = 0;
        if (s == null || s.length() == 0) return maxLen;

        Map<Character, Integer> countMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);

            while (countMap.size() > 2) {
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
