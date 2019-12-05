// 516 https://leetcode.com/problems/longest-palindromic-subsequence/
import java.util.*;
public class LongestPalindromicSubseq {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq(args[0]));
    }

    public static int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0) return 0;
        int length = s.length();
        int[][] dp = new int[length][length];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return lps(s, 0, length - 1, dp);
    }

    private static int lps(String s, int left, int right, int[][] dp) {
        if(dp[left][right] != -1) return dp[left][right];
        if(left > right) return 0;

        int result;
        if(left == right) {
            result = 1;
        } else if(s.charAt(left) == s.charAt(right)){
            result = 2 + lps(s, left + 1, right - 1, dp);
        } 
        else {
            result = Math.max(lps(s, left + 1, right, dp), lps(s, left, right - 1, dp));
        }

        dp[left][right] = result;
        return result;
    }
}