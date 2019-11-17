package solutions.easy;

import solutions.util.ArrayUtils;

import java.util.*;

// 500 https://leetcode.com/problems/keyboard-row/
public class KeyBoardRow {

    private Map<Character, Integer> prepareMap() {

        Map<Character, Integer> characterRowMap = new HashMap<>();

        characterRowMap.put('q', 1);
        characterRowMap.put('w', 1);
        characterRowMap.put('e', 1);
        characterRowMap.put('r', 1);
        characterRowMap.put('t', 1);
        characterRowMap.put('y', 1);
        characterRowMap.put('u', 1);
        characterRowMap.put('i', 1);
        characterRowMap.put('o', 1);
        characterRowMap.put('p', 1);

        characterRowMap.put('a', 2);
        characterRowMap.put('s', 2);
        characterRowMap.put('d', 2);
        characterRowMap.put('f', 2);
        characterRowMap.put('g', 2);
        characterRowMap.put('h', 2);
        characterRowMap.put('j', 2);
        characterRowMap.put('k', 2);
        characterRowMap.put('l', 2);

        characterRowMap.put('z', 3);
        characterRowMap.put('x', 3);
        characterRowMap.put('c', 3);
        characterRowMap.put('v', 3);
        characterRowMap.put('b', 3);
        characterRowMap.put('n', 3);
        characterRowMap.put('m', 3);

        return characterRowMap;
    }

    public String[] findWords(String[] words) {
        Map<Character, Integer> characterMap = prepareMap();
        List<String> res = new ArrayList<>();

        for (String word : words) {
            if (singleRowWord(word, characterMap)) {
                res.add(word);
            }
        }

        return res.toArray(new String[res.size()]);

    }

    private boolean singleRowWord(String word,  Map<Character, Integer> characterMap) {
        word = word.toLowerCase();
        int firstLetterRow = characterMap.get(word.charAt(0));

        for (int i = 1; i < word.length(); i++) {
            if (characterMap.get(word.charAt(i)) != firstLetterRow) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        KeyBoardRow obj = new KeyBoardRow();
        String[] res = obj.findWords(new String[]{"Alaska", "Dad", "Peace", "qwertyuIop", "zx"});
        ArrayUtils.printStringArray(res);
    }

}
