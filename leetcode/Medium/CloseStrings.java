import java.util.*;

// 1657 https://leetcode.com/problems/determine-if-two-strings-are-close/
public class CloseStrings {
    public static void main(String[] args) {
        CloseStrings obj = new CloseStrings();

        System.out.println(obj.closeStrings("cabbba", "abbccc"));
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] word1Freq = new int[26];
        int[] word2Freq = new int[26];

        for (char c : word1.toCharArray()) {
            word1Freq[c - 'a']++;
        }

        for (char c : word2.toCharArray()) {
            if (word1Freq[c - 'a'] == 0)
                return false;
            word2Freq[c - 'a']++;
        }

        Arrays.sort(word1Freq);
        Arrays.sort(word2Freq);

        return Arrays.toString(word1Freq).equals(Arrays.toString(word2Freq));
    }
}