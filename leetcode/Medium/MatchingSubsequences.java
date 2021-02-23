import java.util.*;

// 792 https://leetcode.com/problems/number-of-matching-subsequences/
public class MatchingSubsequences {

    private Map<Character, TreeSet<Integer>> map;

    public static void main(String[] args) {
        MatchingSubsequences obj = new MatchingSubsequences();
        int count = obj.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});
        System.out.println(count);
    }

    public int numMatchingSubseq(String S, String[] words) {
        map = new HashMap<>();

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            map.putIfAbsent(ch, new TreeSet<>());
            map.get(ch).add(i);
        }

        int count = 0;
        for (String word : words) {
            if (isSub(word)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSub(String word) {
        int prevValue = -1;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!map.containsKey(ch)) return false;
            // The indices are stored in ascending order
            // The next character should lie in the index greater than the prev character
            Integer smallestIndexGreaterThanPrev = map.get(ch).ceiling(prevValue + 1);
            if (smallestIndexGreaterThanPrev == null) return false;
            prevValue = smallestIndexGreaterThanPrev;
        }

        return true;
    }
}