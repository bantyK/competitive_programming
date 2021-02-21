//1092 https://leetcode.com/problems/shortest-common-supersequence/
public class ShortestCommonSuperSequence {
    public static void main(String[] args) {
        ShortestCommonSuperSequence obj = new ShortestCommonSuperSequence();
        System.out.println(obj.shortestCommonSupersequence("abac", "cab"));
    }


    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int c = 0; c <= m; c++) {
            dp[0][c] = c;
        }

        for (int r = 0; r <= n; r++) {
            dp[r][0] = r;
        }

        // Find the length of lowest common super sequence
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int minLen = dp[n][m];
        char[] res = new char[minLen];
        int idx = minLen - 1;

        int i = n, j = m;

        // Backtrack the dp array to find the actual sequence
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                // If both chars are equal, select that char and go to diagonally up cell
                res[idx--] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] < dp[i][j - 1]) {
                // choose the minimum from 2 adjacent cells
                res[idx--] = str1.charAt(i - 1);
                i--;
            } else {
                res[idx--] = str2.charAt(j - 1);
                j--;
            }
        }

        // It is possible that we move out of the matrix without covering all chars from
        // both strings. In that case add the remaining chars from both strings
        //otherwise both strings wont be added into the super sequence
        while (i > 0) {
            res[idx--] = str1.charAt(i - 1);
            i--;
        }

        while (j > 0) {
            res[idx--] = str2.charAt(j - 1);
            j--;
        }


        return new String(res);
    }


    // Top Down DP
    private int lengthOfSCSTopDown(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        Integer[][] dp = new Integer[n][m];
        return helper(word1, word2, 0, 0, dp);
    }

    private int helper(String word1, String word2, int index1, int index2, Integer[][] dp) {
        if (index1 == word1.length()) {
            // Word1 has finished. All the remaining chars from word2 needs to be added
            return word2.length() - index2;
        }

        if (index2 == word2.length()) {
            // Word2 has finished. All the remaining chars from word1 needs to be added
            return word1.length() - index1;
        }

        if (dp[index1][index2] != null) return dp[index1][index2];

        if (word1.charAt(index1) == word2.charAt(index2)) {
            // the characters are same, will take this char and recurse for the remaining lengths
            // of the string
            dp[index1][index2] = 1 + helper(word1, word2, index1 + 1, index2 + 1, dp);
        } else {

            // the chars are not same, take both chars one by one and finally choose the one
            // which gives minimum length

            // char from word1 is selected
            int choice1 = 1 + helper(word1, word2, index1 + 1, index2, dp);
            int choice2 = 1 + helper(word1, word2, index1, index2 + 1, dp);

            dp[index1][index2] = Math.min(choice1, choice2);
        }

        return dp[index1][index2];
    }

}
