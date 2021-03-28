import java.util.*;

// 966 https://leetcode.com/problems/vowel-spellchecker/
public class VowelSpellCheck {

    Set<String> matchingWord;
    Map<String, String> capitalCheckMap;
    Map<String, String> vowelsRCheckMap;


    public String[] spellchecker(String[] wordlist, String[] queries) {
        matchingWord = new HashSet<>();
        capitalCheckMap = new HashMap<>();
        vowelsRCheckMap = new HashMap<>();

        for (String word : wordlist) {
            matchingWord.add(word);

            String lower = word.toLowerCase();

            capitalCheckMap.putIfAbsent(lower, word);

            String vowelsRemoved = removeVowel(lower);
            vowelsRCheckMap.putIfAbsent(vowelsRemoved, word);
        }
        String[] res = new String[queries.length];
        int index = 0;

        for (String query : queries) {
            res[index++] = solve(query);
        }

        return res;
    }

    private String solve(String query) {
        String lower = query.toLowerCase();
        String vowelsRemoved = removeVowel(query);

        if (matchingWord.contains(query)) {
            return query;
        }

        if (capitalCheckMap.containsKey(lower)) {
            return capitalCheckMap.get(lower);
        }

        if (vowelsRCheckMap.containsKey(vowelsRemoved)) {
            return vowelsRCheckMap.get(vowelsRemoved);
        }

        return "";
    }

    private String removeVowel(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = isVowel(chars[i]) ? '*' : chars[i];
        }

        return new String(chars);
    }


    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}