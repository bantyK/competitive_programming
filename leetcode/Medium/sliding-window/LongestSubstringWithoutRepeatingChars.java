package sliding;

import java.util.*;

// 3 https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingChars {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChars obj = new LongestSubstringWithoutRepeatingChars();

        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkewfghdl"));
        System.out.println(obj.lengthOfLongestSubstring("dvdf"));
        System.out.println(obj.lengthOfLongestSubstring("bbbb"));
        System.out.println(obj.lengthOfLongestSubstring("aabaab!cbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> indexMap = new HashMap<>();
        int right;
        for (right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (!indexMap.containsKey(rightChar) || indexMap.get(rightChar) < left) {
                indexMap.put(rightChar, right);
            } else {
                maxLen = Math.max(maxLen, right - left);

                int prevIndex = indexMap.get(rightChar);
                if (prevIndex >= left) {
                    left = prevIndex + 1;
                    indexMap.put(rightChar, right);
                }
            }
        }

        return Math.max(maxLen, right - left);

    }
}
