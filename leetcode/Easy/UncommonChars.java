package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UncommonChars {

    public static void main(String[] args) {
        String[] strings = new UncommonChars().uncommonFromSentences("apple apple", "banana");
        for(String str : strings) {
            System.out.println(str);
        }
    }

    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : A.split(" ")) {
            if (wordCountMap.containsKey(word)) {
                wordCountMap.put(word, wordCountMap.get(word) + 1);
            } else {
                wordCountMap.put(word, 1);
            }
        }

        for (String word : B.split(" ")) {
            if (wordCountMap.containsKey(word)) {
                wordCountMap.put(word, wordCountMap.get(word) + 1);
            } else {
                wordCountMap.put(word, 1);
            }
        }
        List<String> result = new ArrayList<>();

        for (String key : wordCountMap.keySet()) {
            if (wordCountMap.get(key) == 1) {
                result.add(key);
            }
        }
        String[] resultArray = new String[result.size()];
        return result.toArray(resultArray);
    }
}
