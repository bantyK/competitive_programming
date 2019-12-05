// 647 https://leetcode.com/problems/palindromic-substrings/
import java.util.*;
public class PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(countSubstrings(args[0]));
    }

    public static int countSubstrings(String s) {
        if(s == null || s.length() == 0) return 0;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];

        for(int j = 0; j < length; ++j) {
            for(int i = 0; i <= j; ++i) {
                if(i == j) dp[i][j] = true;

                else {
                    boolean isInnerStringPalindrome = dp[i+1][j-1] || j - i <= 2;

                    if(s.charAt(i) == s.charAt(j) && isInnerStringPalindrome) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        int result = 0;
        
        for(int i = 0; i < dp.length; ++i) {
            for(int j = 0; j < dp[0].length; ++j) {
                if(dp[i][j]) result++;
            } 
        }
        
        return result;
    }
}