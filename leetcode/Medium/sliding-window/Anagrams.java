package sliding;

import java.util.*;

// 438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class Anagrams {
    public static void main(String[] args) {
        Anagrams obj = new Anagrams();
        System.out.println(obj.findAnagrams("cbaebabacd", "abc"));
        System.out.println(obj.findAnagrams("cbaebabacbd", "abbc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = map.size();

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    counter--;
                }
            }

            while (counter == 0) {
                // check for anagram here because this will be executed only when all chars in string p is found.
                if (right - left + 1 == p.length()) {
                    result.add(left);
                }

                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    if (map.get(leftChar) > 0) {
                        counter++;
                    }
                }

                left++;
            }
        }

        return result;

    }
}

































