import java.util.*;

// 583 https://leetcode.com/problems/delete-operation-for-two-strings/
public class MinDistanceString {
    public static void main(String[] args) {
        MinDistanceString obj = new MinDistanceString();
        System.out.println(obj.minDistance("sea", "eat"));
    }

    public int minDistance(String s1, String s2) {
        int subseqLen = lcs(s1, s2);

        return Math.abs(s1.length() - subseqLen) + Math.abs(s2.length() - subseqLen);
    }

    private int lcs(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        int maxLength = 0;

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(s1.charAt(i -1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

                maxLength = Math.max(dp[i][j], maxLength);
            }
        }

        return maxLength;
    }
}
