import java.util.*;
public class NumMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };

        NumMatrix obj = new NumMatrix(matrix);

        System.out.println(obj.sumRegion(2, 1, 4, 3) == 8);
    }
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        dp = new int[rows + 1][cols + 1];
        
        for(int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0
        }

        for(int j = 1; j < dp[0].length; j++) {
            dp[0][j] = matrix[0][j] + dp[0][j-1];
        }
        
        for(int i = 1; i < dp.length; i++) {
            dp[i][0] = matrix[i][0] + dp[i-1][0];
        }

        for(int i = 1; i < rows; i++) {
            for(int j = 1; j < cols; j++) {
                dp[i][j] = matrix[i][j] + dp[i][j-1];
            }    
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // return dp[row1][col2] - dp[row1-1][col2]
    }
}