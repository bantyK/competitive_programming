import java.util.*;

//159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class LongestSubstringWithAtmost2DistinctChars {
    public static void main(String[] args) {
        LongestSubstringWithAtmost2DistinctChars obj = new LongestSubstringWithAtmost2DistinctChars();
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("aaaabbbavvvvvvvvv"));
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0;
        int windowEnd = 0;
        int maxLen = 0;
        while (windowEnd < s.length()) {
            char endChar = s.charAt(windowEnd);
            windowEnd++;
            map.put(endChar, map.getOrDefault(endChar, 0) + 1);

            while (map.size() > 2) {
                char startChar = s.charAt(windowStart);
                map.put(startChar, map.get(startChar) - 1);
                if (map.get(startChar) == 0) {
                    map.remove(startChar);
                }
                windowStart++;
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart);
        }

        return maxLen;
    }
}
