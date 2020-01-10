import java.util.*;

// https://practice.geeksforgeeks.org/problems/is-sudoku-valid/0/
public class IsSudokuValid {
    public static void main(String[] args) {
        IsSudokuValid obj = new IsSudokuValid();

        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        if (isValid(board)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static boolean isValid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!validPlacement(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean validPlacement(int[][] board, int row, int col) {
        Set<Integer> set = new HashSet<>();
        for (int[] placementRow : board) {
            if (placementRow[col] != 0) {
                if (set.contains(placementRow[col])) {
                    return false;
                } else {
                    set.add(placementRow[col]);
                }
            }
        }

        set.clear();
        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] != 0) {
                if (set.contains(board[row][j])) {
                    return false;
                } else {
                    set.add(board[row][j]);
                }
            }
        }

        set.clear();
        int verticalBoxIndex = row / 3;
        int horizontalBoxIndex = col / 3;

        int leftTopRow = 3 * verticalBoxIndex;
        int leftTopCol = 3 * horizontalBoxIndex;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int num = board[leftTopRow + i][leftTopCol + j];
                if (num != 0) {
                    if (set.contains(num)) {
                        return false;
                    } else {
                        set.add(num);
                    }
                }
            }
        }
        return true;
    }
}
