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
        List<String> results = new ArrayList<>();
        Map<Character, char[]> digitLetterMap = new HashMap<>();
        digitLetterMap.put('1', new char[]{});
        digitLetterMap.put('2', new char[]{'a', 'b', 'c'});
        digitLetterMap.put('3', new char[]{'d', 'e', 'f'});
        digitLetterMap.put('4', new char[]{'g', 'h', 'i'});
        digitLetterMap.put('5', new char[]{'j', 'k', 'l'});
        digitLetterMap.put('6', new char[]{'m', 'n', 'o'});
        digitLetterMap.put('7', new char[]{'p', 'q', 'r', 's'});
        digitLetterMap.put('8', new char[]{'t', 'u', 'v'});
        digitLetterMap.put('9', new char[]{'w', 'x', 'y', 'z'});

        letterCombinationsHelper(digits, digitLetterMap, new StringBuilder(), results);
        return results;
    }

    private void letterCombinationsHelper(String digits, Map<Character, char[]> digitLetterMap, StringBuilder sb, List<String> results) {
        if (digits.length() == sb.length()) {
            results.add(sb.toString());
            return;
        }

        for (char ch : digitLetterMap.get(digits.charAt(sb.length()))) {
            sb.append(ch);
            letterCombinationsHelper(digits, digitLetterMap, sb, results);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}
