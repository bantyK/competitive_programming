import input.TwoDArrayReader;

import java.io.IOException;

//63 https://leetcode.com/problems/unique-paths-ii/
public class UniquePathWithObstacles {
    public static void main(String[] args) throws IOException {
        UniquePathWithObstacles obj = new UniquePathWithObstacles();
        int[][] grid = new TwoDArrayReader().get2DArray();
        int res = obj.uniquePathsWithObstacles(grid);
        System.out.println(res);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        if (rows == 1 && cols == 1) {
            return obstacleGrid[0][0] == 1 ? 0 : 1;
        }

        else if(obstacleGrid[rows-1][cols-1] == 1) {
            return 0;
        }

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = -1;
                }
            }
        }

        // top row
        int j = 0;
        while (j < cols && dp[0][j] != -1) {
            dp[0][j] = 1;
            j++;
        }
        j++;
        while (j < cols) dp[0][j++] = 0;

        // left row
        int i = 0;
        while (i < rows && dp[i][0] != -1) {
            dp[i][0] = 1;
            i++;
        }
        i++;
        while (i < rows) dp[i++][0] = 0;

        for (i = 1; i < rows; i++) {
            for (j = 1; j < cols; j++) {
                if (dp[i][j] != -1) {
                    int left = dp[i][j - 1] > 0 ? dp[i][j - 1] : 0;
                    int top = dp[i - 1][j] > 0 ? dp[i - 1][j] : 0;
                    dp[i][j] = left + top;
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
