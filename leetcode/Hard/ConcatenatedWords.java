import java.util.*;

// 472 https://leetcode.com/problems/concatenated-words/
public class ConcatenatedWords {

    public static void main(String[] args) {
        ConcatenatedWords obj = new ConcatenatedWords();
        System.out.println(obj.findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        Map<String, Boolean> cache = new HashMap<>();
        List<String> res = new ArrayList<>();

        for (String word : words) {
            if (helper(word, wordSet, cache)) {
                res.add(word);
            }
        }

        return res;
    }

    private boolean helper(String word, Set<String> words, Map<String, Boolean> cache) {
        if (cache.containsKey(word)) return cache.get(word);

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            // the prefix should be present in the set, because we have to make words whose
            // parts are present in the array, if the prefix is not present in the array itself, than
            // that's not a valid separation
            if (words.contains(prefix)) {
                String suffix = word.substring(i);
                // we are looking for words which are made of at-least 2 different words in the given array
                // if the suffix is directly present or if parts of this suffix is present then that's a valid concatenated word
                if (words.contains(suffix) || helper(suffix, words, cache)) {
                    cache.put(word, true);
                    return true;
                }
            }
        }
        cache.put(word, false);
        return false;
    }
}