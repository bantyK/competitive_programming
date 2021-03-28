import java.util.*;

//916 https://leetcode.com/problems/word-subsets/
public class WordsSubset {

    public static void main(String[] args) {
        WordsSubset obj = new WordsSubset();
        System.out.println(obj.wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"e", "o"}));
        System.out.println(obj.wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"e", "l"}));
        System.out.println(obj.wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"e", "oo"}));
        System.out.println(obj.wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"lo", "eo"}));
        System.out.println(obj.wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"ec", "oc", "ceo"}));
    }


    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int[] countB = new int[26];

        for (String b : B) {
            int[] counts = getCharCount(b);
            for (int i = 0; i < 26; i++) {
                // minimum count of characters of the strings in A for it to be a super string
                countB[i] = Math.max(countB[i], counts[i]);
            }
        }


        for (String a : A) {
            int[] countA = getCharCount(a);
            boolean ok = true;

            for (int i = 0; i < 26; i++) {
                if (countB[i] > 0 && countA[i] < countB[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) res.add(a);
        }
        return res;
    }

    private int[] getCharCount(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a'] += 1;
        }
        return count;
    }


}