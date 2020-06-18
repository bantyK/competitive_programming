package sw;

import java.util.*;

//438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class AllAnagrams {
    public static void main(String[] args) {
        AllAnagrams obj = new AllAnagrams();

        System.out.println(obj.findAnagrams("cbaebabacd", "abc"));
        System.out.println(obj.findAnagrams("abab", "ab"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int count = countMap.size();
        int left = 0;
        int length = p.length();

        for (int right = 0; right < s.length(); ++right) {
            char rightChar = s.charAt(right);

            if (countMap.containsKey(rightChar)) {
                countMap.put(rightChar, countMap.get(rightChar) - 1);
                if (countMap.get(rightChar) == 0) {
                    --count;
                }
            }


            while (count == 0) {
                if (right - left + 1 == length) {
                    result.add(left);
                }
                char leftChar = s.charAt(left++);
                if (countMap.containsKey(leftChar)) {
                    countMap.put(leftChar, countMap.get(leftChar) + 1);
                    if (countMap.get(leftChar) > 0) {
                        ++count;
                    }
                }
            }
        }

        return result;
    }
}
