package solutions;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
public class CountCharacters {

    public static void main(String[] args) {
        CountCharacters obj = new CountCharacters();
        int count = obj.countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr");
        System.out.println("Count : " + count);
    }

    public int countCharacters(String[] words, String chars) {
        HashMap<Character, Integer> charCount = getCharCountMap(chars);
        int count = 0;
        for (String s : words) {
            count += countCharactersHelper(s, (Map)charCount.clone());
        }
        return count;
    }

    public int countCharactersHelper(String word, Map<Character, Integer> charCount) {
        boolean isValidWord;
        isValidWord = true;
        for (char c : word.toCharArray()) {
            if (!charCount.containsKey(c)) {
                isValidWord = false;
                break;
            } else if (charCount.get(c) <= 0) {
                isValidWord = false;
                break;
            } else {
                charCount.put(c, charCount.get(c) - 1);
            }
        }

        return isValidWord ? word.length() : 0;
    }

    private HashMap<Character, Integer> getCharCountMap(String chars) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : chars.toCharArray()) {
            if (charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
            } else {
                charCount.put(c, 1);
            }
        }
        return charCount;
    }


}
