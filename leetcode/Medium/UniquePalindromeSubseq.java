package revise;

import java.util.Arrays;

// 1930 https://leetcode.com/problems/unique-length-3-palindromic-subsequences/
public class UniquePalindromeSubseq {
    public int countPalindromicSubsequence(String s) {
        // contains the index of the first occurance of each letter in the string
        int[] firstOccurance = new int[26];
        Arrays.fill(firstOccurance, Integer.MAX_VALUE);

        // contains the index of the last occurance of each letter in the string
        int[] lastOccurance = new int[26];

        int n = s.length();

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            firstOccurance[idx] = Math.min(firstOccurance[idx], i);
            lastOccurance[idx] = i;
        }

        int res = 0;
        for (int ch = 0; ch < 26; ch++) {
            int first = firstOccurance[ch];
            int last = lastOccurance[ch];
            if (first < last) {
                boolean[] seen = new boolean[26];
                int distinctLetters = 0;
                // the number of distinct letter between first and last index of a character is the number
                // of 3 digit palindrome that can be formed
                for (int i = first + 1; i < last; i++) {
                    int idx = s.charAt(i) - 'a';
                    if (!seen[idx]) {
                        seen[idx] = true;
                        distinctLetters++;
                    }
                }
                res += distinctLetters;
            }
        }

        return res;
    }
}