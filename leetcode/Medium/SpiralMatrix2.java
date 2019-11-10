package solutions.medium;

import solutions.util.ArrayUtils;

//https://leetcode.com/problems/spiral-matrix-ii/
public class SpiralMatrix2 {
    public static void main(String[] args) {
        SpiralMatrix2 obj = new SpiralMatrix2();

        int[][] ints = obj.generateMatrix(1);
        ArrayUtils.print2DArray(ints);
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int diretion = 0;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int num = 1;

        while (top <= bottom && left <= right) {
            if (diretion == 0) {
                for (int i = left; i <= right; i++) {
                    matrix[top][i] = num++;
                }
                top++;
            } else if (diretion == 1) {
                for (int i = top; i <= bottom; i++) {
                    matrix[i][right] = num++;
                }
                right--;
            } else if (diretion == 2) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++;
                }
                bottom--;
            } else {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }

            diretion = (diretion + 1) % 4;
        }

        return matrix;
    }
}
