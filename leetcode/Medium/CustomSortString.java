import java.util.*;

//791 https://leetcode.com/problems/custom-sort-string/
public class CustomSortString {
    public static void main(String[] args) {
        CustomSortString obj = new CustomSortString();
        System.out.println(obj.customSortString("cba", "cdab"));
        System.out.println(obj.customSortString("dcab", "dhjkhkhajjljcb"));
    }

    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for (char ch : T.toCharArray()) {
            ++count[ch - 'a'];
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : S.toCharArray()) {
            while (count[ch - 'a'] > 0) {
                builder.append(ch);
                count[ch - 'a']--;
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a'] > 0) {
                builder.append(c);
                count[c - 'a']--;
            }
        }

        return builder.toString();
    }

    public String customSortString2(String S, String T) {
        StringBuilder rest = new StringBuilder();
        StringBuilder containing = new StringBuilder();
        for (int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            if (S.indexOf(ch) >= 0) {
                containing.append(ch);
            } else {
                rest.append(ch);
            }
        }
        Character[] chars = new Character[containing.length()];
        for (int i = 0; i < containing.length(); i++) {
            chars[i] = containing.charAt(i);
        }

        Arrays.sort(chars, Comparator.comparingInt(S::indexOf));

        StringBuilder result = new StringBuilder(rest.toString());
        for (char c : chars) {
            result.append(c);
        }

        return result.toString();
    }
}
