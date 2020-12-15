// 132 https://leetcode.com/problems/palindrome-partitioning-ii/
public class PalindromicPartioning {
    public static void main(String[] args) {
        System.out.println(new PalindromicPartioning().minCut("aab"));
        System.out.println(new PalindromicPartioning().minCut("banana"));
        System.out.println(new PalindromicPartioning().minCut("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    public int minCut(String s) {
        int n = s.length();
        // palindrome matrix tells if a string from i to j is a palindrome or not
        boolean[][] palindrome = buildPalinMatrix(s);
        Integer[][] dp = new Integer[n][n];
//        return solve(0, n - 1, dp, palindrome);

        return solveBottomUp(s, palindrome);
    }

    // Bottom Up Approach
    private boolean[][] buildPalinMatrix(String s) {
        int len = s.length();
        boolean[][] mat = new boolean[len][len];
        for (int i = len - 1; i >= 0; --i) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || mat[i + 1][j - 1])) {
                    mat[i][j] = true;
                }
            }
        }
        return mat;
    }

    private int solveBottomUp(String s, boolean[][] palindromeDP) {
        int n = s.length();
        // res[i] denotes the minimum number of cuts required for a string of length i
        int[] res = new int[n];

        for (int j = 0; j < n; j++) {
            int minCut = j;
            for (int i = 0; i <= j; i++) {
                // if the string is not palindrome
                if (palindromeDP[i][j]) {
                    if (i == 0) {
                        // if the whole string from i to j is a palindrome, then there are no cuts required
                        // simply set the value of cuts to 0
                        minCut = 0;
                    } else {
                        // the string from i to j is palindrome, but the string does not start from 0, meaning there is a substring present from 0 to i
                        // we can make a cut there.
                        // since the string from i to j is a palindrome, we dont need any cuts there, cut = 0 for this part of the string
                        // the cut from 0 to i - 1, will be provided by DP table
                        // we need to add 1, because we made cut the string at this point to divide the string from 0 to i -1 and from i to j
                        minCut = Math.min(minCut, res[i - 1] + 1);
                    }
                }
            }
            res[j] = minCut;
        }
        return res[n - 1];
    }

    // Top Bottom Approach.
    private int solve(int i, int j, Integer[][] dp, boolean[][] palindrome) {
        if (i >= j || palindrome[i][j]) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int minCut = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cutFromLeft = solve(i, k, dp, palindrome);
            int cutFromRight = solve(k + 1, j, dp, palindrome);
            minCut = Math.min(minCut, 1 + cutFromLeft + cutFromRight);
        }

        return dp[i][j] = minCut;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

}