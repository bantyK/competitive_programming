import java.util.*;

// 809 https://leetcode.com/problems/expressive-words/
public class ExpressiveWord {
    public static void main(String[] args) {
        ExpressiveWord obj = new ExpressiveWord();

        String s = "dddiiiinnssssssoooo";
        String[] words = new String[]{"dinnssoo", "ddinso", "ddiinnso", "ddiinnssoo", "ddiinso", "dinsoo", "ddiinsso", "dinssoo", "dinso"};
        System.out.println(obj.expressiveWords(s, words));

        s = "heeelllooo";
        words = new String[]{"hellllo"};
        System.out.println(obj.expressiveWords(s, words));

    }

    // each group of continuous chars are a segment¬
    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (word.length() > s.length()) continue;
            if (expressive(s, word)) {
                count++;
            }
        }
        return count;
    }

    private boolean expressive(String str, String word) {
        int i = 0; // index for str
        int j = 0; // index for word

        while (j < word.length()) {
            char c = word.charAt(j);
            int start = j;
            while (j < word.length() && word.charAt(j) == c) {
                j++;
            }
            int len = j - start;
            if (str.charAt(i) != c) return false;
            int count = 0;
            while (i < str.length() && str.charAt(i) == c) {
                i++;
                count++;
            }

            if (count < 3 && count != len || count >= 3 && len > count) return false;
        }

        return true;
    }

}
