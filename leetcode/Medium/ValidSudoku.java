import java.util.*;

//36 https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
    public static void main(String[] args) {
        ValidSudoku obj = new ValidSudoku();

        char[][] board = new char[][]
                {
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


        System.out.println(obj.isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {

        // check squares
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!validSquare(board, i, j)) {
                    return false;
                }
            }
        }

        //check rows
        for (int row = 0; row < 9; row++) {
            if (!validRow(board, row)) return false;
        }

        // check columns
        for (int col = 0; col < 9; col++) {
            if (!validColumn(board, col)) return false;
        }

        return true;
    }

    private boolean validColumn(char[][] board, int col) {
        HashSet<Character> values = new HashSet<>();

        for (int row = 0; row < 9; row++) {
            if (board[row][col] != '.') {
                if (values.contains(board[row][col])) {
                    return false;
                } else {
                    values.add(board[row][col]);
                }
            }
        }

        return true;
    }

    private boolean validRow(char[][] board, int row) {
        Set<Character> values = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            if (board[row][col] != '.') {
                if (values.contains(board[row][col])) {
                    return false;
                } else {
                    values.add(board[row][col]);
                }
            }
        }

        return true;
    }

    private boolean validSquare(char[][] board, int squareX, int squareY) {
        Set<Character> values = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println((3 * squareX + i) + ", " + (3 * squareY + j));
                char val = board[3 * squareX + i][3 * squareY + j];
                if (val != '.') {
                    if (values.contains(val)) return false;
                    else values.add(val);
                }
            }
        }

        return true;
    }

}
