import java.util.*;

// 438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagrams {
    public static void main(String[] args) {
        FindAllAnagrams obj = new FindAllAnagrams();
        System.out.println(obj.findAnagrams("abab","ab"));
    }


    /**
    * Sliding window technique
    */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;

        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            int prevCount = charMap.getOrDefault(c, 0);
            charMap.put(c, prevCount + 1);
        }
        int size = charMap.size();

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (charMap.containsKey(rightChar)) {
                charMap.put(rightChar, charMap.get(rightChar) - 1);
                if (charMap.get(rightChar) == 0) {
                    size--;
                }
            }


            while (size == 0) {
                if (right - left + 1 == p.length()) {
                    result.add(left);
                }


                char leftChar = s.charAt(left);
                if (charMap.containsKey(leftChar)) {
                    charMap.put(leftChar, charMap.get(leftChar) + 1);
                    if (charMap.get(leftChar) > 0)
                        size++;
                }
                left++;
            }
        }

        return result;
    }

    public List<Integer> findAnagrams(String b, String a) {
        int lenA = a.length();
        int lenB = b.length();
        List<Integer> result = new ArrayList<>();
        if (lenA > lenB) return result;

        int[] charsCount = new int[26];
        int[] window = new int[26];

        for (char c : a.toCharArray()) {
            charsCount[c - 'a']++;
        }

        for (int i = 0; i < lenA; i++) {
            window[b.charAt(i) - 'a']++;
        }

        int i = 0;
        for (; i < b.length() - lenA; i++) {
            if (same(charsCount, window)) {
                result.add(i);
            }
            window[b.charAt(i) - 'a']--;
            window[b.charAt(i + lenA) - 'a']++;
        }

        if (same(charsCount, window)) {
            result.add(i);
        }
        return result;
    }


    private boolean same(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }
}
