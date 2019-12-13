package solutions.medium;

import java.util.*;

// 17. https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations obj = new LetterCombinations();
        for (String s : obj.letterCombinations("91")) {
            System.out.print(s + " ");
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return Collections.emptyList();
        Map<Character, List<Character>> digitCombinationMap = new HashMap<>();
        digitCombinationMap.put('2', Arrays.asList('a', 'b', 'c'));
        digitCombinationMap.put('3', Arrays.asList('d', 'e', 'f'));
        digitCombinationMap.put('4', Arrays.asList('g', 'h', 'i'));
        digitCombinationMap.put('5', Arrays.asList('j', 'k', 'l'));
        digitCombinationMap.put('6', Arrays.asList('m', 'n', 'o'));
        digitCombinationMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        digitCombinationMap.put('8', Arrays.asList('t', 'u', 'v'));
        digitCombinationMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> combinations = new ArrayList<>();
        generateCombinations(digits, digitCombinationMap, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private void generateCombinations(String digits, Map<Character, List<Character>> map, int currentDigitIndex, StringBuilder currentCombination, List<String> combinations) {
        if (currentCombination.length() == digits.length()) {
            combinations.add(currentCombination.toString());
            return;
        }

        char currentDigit = digits.charAt(currentDigitIndex);
        for (char c : map.get(currentDigit)) {
            currentCombination.append(c);
            generateCombinations(digits, map, currentDigitIndex + 1, currentCombination, combinations);
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}
