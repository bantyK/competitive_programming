
// 304 https://leetcode.com/problems/range-sum-query-2d-immutable/
public class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        final int rows = matrix.length;
        if (rows > 0) {
            final int cols = matrix[0].length;
            if (cols > 0) {
                dp = new int[rows][cols];

                populateDp(matrix, rows, cols);
            }
        }
    }

    private void populateDp(int[][] matrix, int rows, int cols) {
        dp[0][0] = matrix[0][0];

        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row - 1][0] + matrix[row][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row][col] = matrix[row][col] + dp[row - 1][col] + dp[row][col - 1] - dp[row - 1][col - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = dp[row2][col2];

        if (col1 > 0) {
            res -= dp[row2][col1 - 1];
        }
        if (row1 > 0) {
            res -= dp[row1 - 1][col2];
        }

        if (row1 > 0 && col1 > 0) {
            res += dp[row1 - 1][col1 - 1];
        }

        return res;
    }
}
