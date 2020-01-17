import input.Util;

// 221 https://leetcode.com/problems/maximal-square/
public class MaximalSquare {
    public static void main(String[] args) {
        MaximalSquare obj = new MaximalSquare();
        char[][] matrix = {
                {'1'}
        };

        /**
         * {'1', '0', '1', '0', '0'},
         *                 {'1', '0', '1', '1', '1'},
         *                 {'1', '1', '1', '1', '1'},
         *                 {'1', '0', '0', '1', '0'}
         */
        System.out.println(obj.maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0) return 0;

        int[][] dp = new int[rows][cols];
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) dp[i][j] = Character.getNumericValue(matrix[i][j]);
                else {
                    if (matrix[i][j] != '0') {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    }
                }

                res = Math.max(dp[i][j], res);
            }
        }
        Util.print2dArray(dp);
        return res;
    }
}
