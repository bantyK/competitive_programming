package solutions.easy;

import java.util.*;

// https://leetcode.com/problems/most-common-word/
public class MostCommonWord {
    public static void main(String[] args) {
        MostCommonWord obj = new MostCommonWord();
        System.out.println(
                obj.mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"})
        );
    }

    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> wordsCountMap = new HashMap<>();

        String modifiedParagraph = paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase();
        for (String word : modifiedParagraph.split(" ")) {
            if (!bannedWords.contains(word))
                wordsCountMap.put(word, wordsCountMap.getOrDefault(word, 0) + 1);
        }

        String result = " ";
        for (String key : wordsCountMap.keySet()) {
            if (result.equals(" ") || wordsCountMap.get(key) > wordsCountMap.get(result)) {
                result = key;
            }
        }
        return result;
    }



}
