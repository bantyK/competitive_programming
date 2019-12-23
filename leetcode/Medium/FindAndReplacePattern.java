import java.util.*;

// 890 https://leetcode.com/problems/find-and-replace-pattern/
public class FindAndReplacePattern {
    public static void main(String[] args) {
        FindAndReplacePattern obj = new FindAndReplacePattern();

        String[] words = new String[]{"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";

        System.out.println(obj.findAndReplacePattern(words, pattern));
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> results = new ArrayList<>();
        if (words.length == 0) return results;

        String patternString = getWordCountString(pattern);
        for (String word : words) {
            String wordCountString = getWordCountString(word);
            if (wordCountString.equals(patternString)) {
                results.add(word);
            }
        }
        return results;
    }

    private String getWordCountString(String word) {
        int index = 0;
        Map<Character, Integer> map = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, index);
                builder.append(index);
                index++;
            } else {
                builder.append(map.get(c));
            }
        }
        return builder.toString();
    }
}
