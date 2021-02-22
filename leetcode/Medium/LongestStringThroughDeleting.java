import java.util.*;

// 524 https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
public class LongestStringThroughDeleting {

    public static void main(String[] args) {
        LongestStringThroughDeleting obj = new LongestStringThroughDeleting();
        System.out.println(obj.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        System.out.println(obj.findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));

    }

    public String findLongestWord(String s, List<String> d) {
        String res = "";

        for (String word : d) {
            if (isSubsequence(s, word)) {
                if (res.length() <= word.length()) {
                    if (res.length() < word.length()) {
                        res = word;
                    } else {
                        res = word.compareTo(res) > 0 ? res : word;
                    }
                }
            }
        }

        return res;
    }

    /**
     * returns true if t is a sub sequence of s
     */
    private boolean isSubsequence(String s, String t) {
        int tIndex = 0;
        int sIndex = 0;
        while (sIndex < s.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                tIndex++;
                if (tIndex == t.length()) {
                    return true;
                }
            }
            sIndex++;
        }

        return false;
    }

}