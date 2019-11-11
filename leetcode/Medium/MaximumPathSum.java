package solutions.medium;

// https://leetcode.com/problems/minimum-path-sum/
public class MaximumPathSum {

    public static void main(String[] args) {
        MaximumPathSum obj = new MaximumPathSum();

        int[][] grid = new int[][]{
                {3, 2, 1, 3},
                {1, 9, 2, 3},
                {9, 1, 5, 4}
        };

        System.out.println(
                obj.minPathSum(grid)
        );
    }

    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int dp[][] = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j != 0) {
                    // top row, except the first element
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0 && i != 0) {
                    // top column, except the first element
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i == 0) {
                    // top left element
                    dp[i][i] = grid[i][j];
                } else {
                    // rest of the columns
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }
}
