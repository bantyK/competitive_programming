
import java.util.*;

//764 https://leetcode.com/problems/largest-plus-sign/
public class LargestPlusSign {
    public static void main(String[] args) {
        LargestPlusSign obj = new LargestPlusSign();
        int[][] mines = new int[][]{{0,0}};
        int res = obj.orderOfLargestPlusSign(1, mines);
        System.out.println(res);
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] matrix = new int[n][n];
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }

        for (int[] mine : mines) {
            int x = mine[0];
            int y = mine[1];

            matrix[x][y] = 0;
        }

        int[][] leftRight = new int[n][n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    count += 1;
                } else {
                    count = 0;
                }

                leftRight[i][j] = count;
            }
        }

        int[][] rightLeft = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    count += 1;
                } else {
                    count = 0;
                }

                rightLeft[i][j] = count;
            }
        }

        int[][] topDown = new int[n][n];
        for(int j = 0; j < n; j++) {
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(matrix[i][j] != 0) {
                    count += 1;
                } else {
                    count = 0;
                }

                topDown[i][j] = count;
            }
        }
        int[][] downTop = new int[n][n];
        for(int j = n - 1; j >= 0; j--) {
            int count = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(matrix[i][j] != 0) {
                    count += 1;
                } else {
                    count = 0;
                }
                downTop[i][j] = count;
            }
        }

        int res = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = Math.min(Math.min(leftRight[i][j], rightLeft[i][j]),Math.min(topDown[i][j], downTop[i][j]));
                res = Math.max(res,matrix[i][j]);
            }
        }

        return res;
    }
}
