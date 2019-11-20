// 1143: https://leetcode.com/problems/longest-common-subsequence/
public class LCS {
    public static void main(String[] args) {
        int lcs = longestCommonSubsequence("abc", "def");
        System.out.println(lcs);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len2+1][len1+1];

        // First row and column of DP table is for empty string, hence it will be all 0.
        for(int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int j = 0; j < dp.length; j++) {
            dp[j][0] = 0;
        }
        
        int index1 = 0;
        int index2 = 0;
        for(int i = 1; i < dp.length; i++) {
            index1 = 0;
            for(int j = 1; j < dp[i].length; j++) {
                if(index1 < text1.length() && index2 < text2.length() && text1.charAt(index1) == text2.charAt(index2)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                index1++;
            }
            index2++;
        }
        return dp[len2][len1];
    }
}