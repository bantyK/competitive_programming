import java.util.*;

// 37 https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    private static final char EMPTY_ENTRY = '.';

    public static void main(String[] args) {
        SudokuSolver obj = new SudokuSolver();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        obj.solveSudoku(board);

        for(char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void solveSudoku(char[][] board) {
        canSolveSudokuFromCell(board, 0, 0);
    }

    private boolean canSolveSudokuFromCell(char[][] board, int row, int col) {
        // Base case: if overshoot the column, move to the next row
        if (col == board[row].length) {
            col = 0;
            row++;

            if (row == board.length) {
                return true;
            }
        }

        // skip the cell which already has entries.
        if (board[row][col] != EMPTY_ENTRY) {
            return canSolveSudokuFromCell(board, row, col + 1);
        }

        for (int value = 1; value <= board.length; value++) {
            char charToPlace = (char) (value + '0');

            if (canPlaceValue(board, row, col, charToPlace)) {
                board[row][col] = charToPlace;
                if (canSolveSudokuFromCell(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = EMPTY_ENTRY;
            }
        }
        return false;
    }

    private boolean canPlaceValue(char[][] board, int row, int col, char charToPlace) {
        // check column of placement
        for (char[] placementRow : board) {
            if (charToPlace == placementRow[col]) {
                return false;
            }
        }

        // check row of the placement
        for (int i = 0; i < board[row].length; i++) {
            if (charToPlace == board[row][i]) {
                return false;
            }
        }

        int regionSize = 3;

        int verticalBoxIndex = row / regionSize;
        int horizontalBoxIndex = col / regionSize;


        int topLeftOfSubBoxRow = regionSize * verticalBoxIndex;
        int topLeftOfSubBoxCol = regionSize * horizontalBoxIndex;

        for (int i = 0; i < regionSize; i++) {
            for (int j = 0; j < regionSize; j++) {
                if (charToPlace == board[topLeftOfSubBoxRow + i][topLeftOfSubBoxCol + j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
