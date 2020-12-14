import java.util.*;

// 131 https://leetcode.com/problems/palindrome-partitioning/
// Solution using both backtracking and backtracking + DP
public class PalindromicPartitioning {
    public static void main(String[] args) {
        PalindromicPartitioning obj = new PalindromicPartitioning();
        System.out.println(obj.partition("aab"));
    }

    // Driver function
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        List<String> current = new ArrayList<>();
        boolean[][] dp = new boolean[len][len];
        helper(s, 0, current, res, dp);
        return res;
    }


    // Dp solution
    // Dp solution

    private void helper(String s, int start, List<String> palindromicSnippets, List<List<String>> res, boolean[][] dp) {
        if (start == s.length()) {
            // exhausted the string, so we have all the palindromic partitions, add them in the result list
            res.add(new ArrayList<>(palindromicSnippets));
            return;
        }

        // start looking from start index until the length of the string
        for (int end = start; end < s.length(); end++) {
            // if the string from start index to the current end is a plaindrome
            // add that string into our list of current decompositions and recurse on the rest of the string
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                String palindromicSnippet = s.substring(start, end + 1);
                palindromicSnippets.add(palindromicSnippet);
                // recurse on the rest of the string
                helper(s, end + 1, palindromicSnippets, res, dp);
                // backtrack. remove the last used snippet and continue recursion
                palindromicSnippets.remove(palindromicSnippets.size() - 1);
            }
        }
    }

    // Backtracking
    // This does not require the DP cache
    private void backTrackHelper(String s, int start, List<String> palindromicSnippets, List<List<String>> res) {
        if (start == s.length()) {
            // exhausted the string, so we have all the palindromic partitions, add them in the result list
            res.add(new ArrayList<>(palindromicSnippets));
            return;
        }

        // start looking from start index until the length of the string
        for (int end = start; end < s.length(); end++) {
            // if the string from start index to the current end is a plaindrome
            // add that string into our list of current decompositions and recurse on the rest of the string
            if (isPalindrome(s, start, end)) {
                String palindromicSnippet = s.substring(start, end + 1);
                palindromicSnippets.add(palindromicSnippet);
                // recurse on the rest of the string
                backTrackHelper(s, end + 1, palindromicSnippets, res);
                // backtrack. remove the last used snippet and continue recursion
                palindromicSnippets.remove(palindromicSnippets.size() - 1);
            }
        }
    }

    // helper function to test if a string from i to j is a palindrome or not
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

}