import java.util.*;

//1035 https://leetcode.com/problems/uncrossed-lines/
public class UnCrossedLines {
    public static void main(String[] args) {
        UnCrossedLines obj = new UnCrossedLines();
        int[] A = new int[]{1, 18, 8, 9, 16, 7, 18, 2, 17, 20, 15, 11, 19, 16, 19, 20, 4, 17, 5, 20, 4, 2, 18, 3, 4, 12, 4, 13, 7, 7, 2, 3, 3, 4, 11, 1, 20, 18, 14, 4, 10, 19, 20, 20, 16, 19, 18, 18, 15, 16, 18, 9, 15, 7, 7, 13, 9, 2, 5, 19, 13, 20, 18, 20, 3, 6, 2, 15, 3, 7, 7, 18, 14, 8, 15, 3, 11, 13, 2, 6, 1, 19, 12, 20, 8, 7, 2, 1, 10, 15, 19, 15, 7, 1, 4, 1, 12, 13, 8, 11, 7, 10, 1, 15, 19, 10, 14, 20, 15, 15, 4, 3, 18, 8, 20, 9, 11, 19, 1, 20, 20, 9, 5, 9, 16, 18, 6, 11, 15, 11, 12, 1, 1, 9, 14, 10, 6, 6, 20, 12, 7, 1, 12, 2, 15, 12, 4, 5, 5, 14, 7, 17, 13, 2, 10, 16, 3, 4, 16, 14, 14, 3, 4, 15, 10, 15, 12, 20, 14, 17, 16, 18, 18, 16, 12, 13, 16, 15, 9, 17, 9, 12, 1, 9, 13, 2, 2, 17, 20, 14, 16, 16, 5, 13, 10, 5, 20, 4, 7, 11};
        int[] B = new int[]{3, 1, 9, 18, 11, 2, 14, 17, 12, 6, 3, 17, 16, 14, 14, 17, 19, 15, 6, 5, 15, 19, 17, 16, 4, 6, 5, 14, 16, 10, 19, 10, 14, 19, 19, 9, 11, 18, 16, 1, 17, 10, 7, 13, 5, 1, 10, 14, 8, 11, 15, 5, 2, 13, 7, 20, 3, 6, 19, 7, 1, 1, 18, 18, 11, 20, 17, 10, 12, 13, 11, 6, 11, 16, 7, 1, 6, 1, 9, 2, 13, 8, 11, 13, 12, 8, 4, 8, 10, 10, 2, 20, 9, 8, 8, 6, 6, 11, 18, 17};

        System.out.println(obj.maxUncrossedLines(A, B));
    }


    // Longest common subsequence
    public int maxUncrossedLines(int[] A, int[] B) {
        Integer[][] dp = new Integer[A.length][B.length];
        return helper(A, B, 0, 0, dp);
    }

    private int helper(int[] A, int[] B, int i, int j, Integer[][] dp) {
        if (i == A.length || j == B.length) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (A[i] == B[j]) {
            return dp[i][j] = 1 + helper(A, B, i + 1, j + 1, dp);
        }

        int c1 = helper(A, B, i + 1, j, dp);
        int c2 = helper(A, B, i, j + 1, dp);

        return dp[i][j] = Math.max(c1, c2);
    }
}
