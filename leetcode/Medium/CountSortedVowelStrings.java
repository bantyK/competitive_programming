import java.util.*;

// 1641 https://leetcode.com/problems/count-sorted-vowel-strings/
public class CountSortedVowelStrings {
    public static void main(String[] args) {
        CountSortedVowelStrings obj = new CountSortedVowelStrings();
        System.out.println(obj.countVowelStringsDP(3));
    }

    private final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
    private int count = 0;
    private int n;

    public int countVowelStrings(int n) {
        count = 0;
        this.n = n;
        helper(0, new StringBuilder());
        return count;
    }

    private void helper(int index, StringBuilder builder) {
        if (builder.length() == n) {
            this.count++;
            return;
        }
        for (int i = index; i < vowels.size(); i++) {
            builder.append(vowels.get(i));
            helper(i, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    // DP version improved
    public int countVowelStringsDP(int n) {
        int countA = 1, countE = 1, countI = 1, countO = 1, countU = 1;
        int i = 2;
        while (n > 1) {
            countA = countA + countE + countI + countO + countU;
            countE = countE + countI + countO + countU;
            countI = countI + countO + countU;
            countO = countO + countU;
            // countU will always be one because we are considering lexicographically
            // sorrted string
            System.out.println("Count of characters with " + (i++) + " characters = "
                    + (countA + countE + countI + countO + countU));
            n--;
        }

        return countA + countE + countI + countO + countU;
    }
}