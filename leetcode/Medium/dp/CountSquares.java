// 1277 : https://leetcode.com/problems/count-square-submatrices-with-all-ones/
public class CountSquares {
    public static void main(String[] args) {
        CountSquares obj = new CountSquares();
        int[][] matrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 0},
        };

        int numSquares = obj.countSquares(matrix);
        System.out.println(numSquares);
    }

    public int countSquares(int[][] matrix) {
        int squares = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) dp[i][0] = matrix[i][0];
        for (int j = 0; j < cols; j++) dp[0][j] = matrix[0][j];

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }


//        printdp(dp);

        for (int[] ints : dp) {
            for (int j = 0; j < ints.length; j++) {
                squares += ints[j];
            }
        }

        return squares;
    }

    private void printdp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
