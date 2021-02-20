import java.util.*;

//72 https://leetcode.com/problems/edit-distance/
public class EditDistance {
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        System.out.println(obj.minDistance("A", "B"));
        System.out.println(obj.minDistance("intention", "execution"));
        System.out.println(obj.minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) return word2.length();
        if (word2 == null || word2.length() == 0) return word1.length();
        Map<String, Integer> cache = new HashMap<>();
        return match2(word1, word2, word1.length() - 1, word2.length() - 1, cache);
    }


    private int match2(String s1, String s2, int len1, int len2, Map<String, Integer> cache) {
        // len1 and len2 represent the last characters of string1 and string2.
	// we will start our comparison from the end of both strings.
 
		if (len1 < 0) {
            return len2 + 1;
        }
        if (len2 < 0) {
            return len1 + 1;
        }
        String key = len1 + "" + len2;

	// if we have already solved for the current values of len1 and len2, then we will have the solution stored in the cache. No need of any further calculation, simply return
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int res = 0;

        if (s1.charAt(len1) == s2.charAt(len2)) {
            res = match2(s1, s2, len1 - 1, len2 - 1, cache);
        } else {
            // we have added a character(the last char of string2) into string1, so the last chars of string1 and string2 are matching,
            // we need to compare the rest of the string2 which is the entire string2 minus the last char(len2 - 1) and rest of the string1
            // which is still len1 because a char is added at the end. So we won't subtract 1 from len1 (len1 - 1 + 1). +1 because a char is added
            int insert = match2(s1, s2, len1, len2 - 1, cache);

            // we have deleted a char from string1, so now we will match the rest of char1(which will be entire string1 minus last char) and
            // all of char2
            int delete = match2(s1, s2, len1 - 1, len2, cache);

            // we will replace the char at string1 with string2. So both will be subtracted
            int replace = match2(s1, s2, len1 - 1, len2 - 1, cache);

            res = 1 + Math.min(Math.min(insert, replace), delete);
        }

	// before returning the result, store it in the cache so as to use it later.

        cache.put(key, res);
        return res;
    }

}
