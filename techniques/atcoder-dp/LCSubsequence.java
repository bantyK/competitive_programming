package atcoder;

import java.util.Scanner;

//https://atcoder.jp/contests/dp/tasks/dp_f
public class LCSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();

        System.out.println(lcs(s, t));
    }

    private static String lcs(String s, String t) {
        final int len1 = s.length();
        final int len2 = t.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int r = 1; r <= len1; r++) {
            for (int c = 1; c <= len2; c++) {
                if (s.charAt(r - 1) == t.charAt(c - 1)) {
                    dp[r][c] = 1 + dp[r - 1][c - 1];
                } else {
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
                }
            }
        }

        int index = dp[len1][len2];
        char[] lcs = new char[index];
        int i = len1;
        int j = len2;
        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                lcs[--index] = s.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(lcs);
    }


}
