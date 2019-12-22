import java.util.*;

// 438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagrams {
    public static void main(String[] args) {
        FindAllAnagrams obj = new FindAllAnagrams();
        System.out.println(obj.findAnagrams("abab","ab"));
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
