import java.util.*;

// 423 https://leetcode.com/problems/reconstruct-original-digits-from-english/
public class ReconstructOriginalDigit {

    public String originalDigits(String s) {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] counts = new int[10];

        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a'] += 1;
        }

        // consider those characters which apper only once in the digits

        counts[0] = charCounts['z' - 'a']; // 'z' uniquely represents a zero
        counts[2] = charCounts['w' - 'a']; // 'w' uniquely represents a two
        counts[4] = charCounts['u' - 'a']; // 'u' uniquely represents a four
        counts[6] = charCounts['x' - 'a']; // 'x' uniquely represents a six
        counts[8] = charCounts['g' - 'a']; // 'g' uniquely represents a eight

        // remove 0, 2, 4, 6, 8 from counts
        for (int i = 0; i < 10; i++) {
            String word = digits[i];
            for (char c : word.toCharArray()) {
                charCounts[c - 'a'] -= counts[i];
            }
        }

        // once 0, 2, 4, 6, 8 is counted, we can uniquely count others
        counts[1] = charCounts['o' - 'a']; //  once 0, 2 and 4 is done, now '0' can only means 1
        counts[3] = charCounts['r' - 'a']; //  once 0 is done, now 'r' can only means 3
        counts[5] = charCounts['f' - 'a']; //  once 4 is done, now 'f' can only means 5
        counts[7] = charCounts['s' - 'a']; //  once 6 is done, now 's' can only means 7

        for (int i = 1; i <= 7; i += 2) {
            String word = digits[i];
            for (char c : word.toCharArray()) {
                charCounts[c - 'a'] -= counts[i];
            }
        }

        // only 9 is remaining, count of 9 will be count of 'i' or 'e'. Not 'n' because 9 contains 2 'n'
        counts[9] = charCounts['e' - 'a'];

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < counts[i]; j++)
                builder.append(i);
        }

        return builder.toString();

    }
}