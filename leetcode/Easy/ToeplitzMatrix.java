package solutions.easy;

import java.util.*;

// 766 https://leetcode.com/problems/toeplitz-matrix/
public class ToeplitzMatrix {
    public static void main(String[] args) {
        ToeplitzMatrix obj = new ToeplitzMatrix();
        int[][] matrix = new int[][]{
                {1},
                {2},
                {3}
        };

        System.out.println(obj.isToeplitzMatrix(matrix));
    }

    public boolean isToeplitzMatrix(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        List<List<Integer>> diagonals = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Integer> currentDiagonal = new ArrayList<>();
            int col = 0;
            for (int r = i; r < rows; r++) {
                if(col >= cols) break;
                currentDiagonal.add(matrix[r][col++]);
            }

            diagonals.add(currentDiagonal);
        }

        for (int j = 1; j < cols; j++) {
            int row = 0;
            List<Integer> currentDiagonal = new ArrayList<>();
            for (int c = j; c < cols; c++) {
                if (row >= rows) break;
                currentDiagonal.add(matrix[row++][c]);
            }

            diagonals.add(currentDiagonal);
        }


        for (List<Integer> diagonal : diagonals) {
            for (int integer : diagonal) {
                if (integer != diagonal.get(0)) {
                    return false;
                }
            }
        }

        return true;

    }
}

