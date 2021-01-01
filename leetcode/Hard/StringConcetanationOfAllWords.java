import java.util.*;

//30 https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class StringConcetanationOfAllWords {

    public static void main(String[] args) {
        StringConcetanationOfAllWords obj = new StringConcetanationOfAllWords();
        System.out.println(obj.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();

        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }

        Map<String, Integer> countMap = new HashMap<>();

        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        int sLen = s.length();
        // length of a single word in the words array, they are all of same length
        int singleWordLength = words[0].length();
        int wordsCount = words.length; // number of words in the words array

        // We will basically take substrings of length (singleWordLength * wordsCount)
        // and check if that substring contains all words from the words array

        for (int i = 0; i < sLen - singleWordLength * wordsCount + 1; i++) {
            // subs is a substring of the original string which has the same number of words
            // as the words array
            String subs = s.substring(i, i + singleWordLength * wordsCount);

            // we will check if this current substring has all the words from the words
            // table,
            // if there is, we will add this word in our res array list
            if (substringContainsAllWords(subs, singleWordLength, countMap)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean substringContainsAllWords(String word, int wordLen, Map<String, Integer> countMap) {
        // this map will keep track of the count of the words in the passed substring
        Map<String, Integer> currentStringCountMap = new HashMap<>();

        for (int i = 0; i < word.length(); i += wordLen) {
            String substring = word.substring(i, i + wordLen);
            currentStringCountMap.put(substring, currentStringCountMap.getOrDefault(substring, 0) + 1);
        }

        return currentStringCountMap.equals(countMap);
    }
}