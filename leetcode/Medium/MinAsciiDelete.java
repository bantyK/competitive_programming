// 712 https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
public class MinAsciiDelete {
    public static void main(String[] args) {
        MinAsciiDelete obj = new MinAsciiDelete();
        System.out.println(obj.minimumDeleteSumBottomUp("sea", "eat"));
        System.out.println(obj.minimumDeleteSumBottomUp("delete", "leet"));
    }

    //////////////// Bottom UP approach ////////////////
    public int minimumDeleteSumBottomUp(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initializing first row
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // Initializing first column
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int option1 = dp[i-1][j] + s1.charAt(i - 1); // char from s1 is removed. s1 is at row side
                    int option2 = dp[i][j - 1] + s2.charAt(j - 1); // char from s1 is removed. s1 is at row side
                    dp[i][j] = Math.min(option1, option2);
                }
            }
        }

        return dp[m][n];
    }
    //////////////// Top down approach ////////////////

    private String s1;
    private String s2;

    public int minimumDeleteSumTopDown(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length() + 1][s2.length() + 1];
        this.s1 = s1;
        this.s2 = s2;

        return helper(0, 0, dp);
    }


    private int helper(int index1, int index2, Integer[][] dp) {

        if (dp[index1][index2] != null) return dp[index1][index2];

        int cost = 0;
        if (index1 == s1.length() && index2 == s2.length()) {
            //Both the strings are done. The diff is 0
            return 0;
        } else if (index1 == s1.length()) {
            // First string is finished. Second string is still remaining, so add the char of second string
            cost = s2.charAt(index2) + helper(index1, index2 + 1, dp);
        } else if (index2 == s2.length()) {
            // Second string is finished. First string is still remaining, so add the char of first string
            cost = s1.charAt(index1) + helper(index1 + 1, index2, dp);
        } else if (s1.charAt(index1) == s2.charAt(index2)) {
            cost = helper(index1 + 1, index2 + 1, dp);
        } else {
            // try deleting chars from both the strings, whichever gives the minimum result, take that
            int option1 = s1.charAt(index1) + helper(index1 + 1, index2, dp); // deleting char from string1
            int option2 = s2.charAt(index2) + helper(index1, index2 + 1, dp); // // deleting char from string2

            cost = Math.min(option1, option2);
        }
        return dp[index1][index2] = cost;
    }
}
