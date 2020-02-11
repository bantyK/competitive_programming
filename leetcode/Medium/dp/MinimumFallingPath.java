//931 https://leetcode.com/problems/minimum-falling-path-sum/
public class MinimumFallingPath {

    public int minFallingPathSum(int[][] a) {
        int rows = a.length;
        if (rows == 0) return 0;
        int cols = a[0].length;
        if (cols == 0) return 0;

        int[][] dp = new int[rows][cols];

        for (int col = 0; col < cols; col++) {
            dp[0][col] = a[0][col];
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int val = dp[r - 1][c];
                if (c - 1 >= 0) val = Math.min(val, dp[r - 1][c - 1]);
                if (c + 1 < cols) val = Math.min(val, dp[r - 1][c + 1]);

                dp[r][c] = a[r][c] + val;
            }
        }

        int res = dp[rows - 1][0];

        for (int c = 1; c < cols; c++) {
            res = Math.min(res, dp[rows - 1][c]);
        }

        return res;
    }
}
