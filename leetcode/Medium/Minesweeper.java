import java.util.*;

//529 https://leetcode.com/problems/minesweeper/
public class Minesweeper {
    public static void main(String[] args) {
        Minesweeper obj = new Minesweeper();
        char[][] board = new char[][]{
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        int[] click = new int[]{3, 0};

        char[][] updated = obj.updateBoard(board, click);

        for (int i = 0; i < updated.length; i++) {
            for (int j = 0; j < updated[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board.length == 0 || click.length <= 1) return board;

        int x = click[0];
        int y = click[1];

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return board;

        if (board[x][y] == 'M') {
            // a mine has been clicked
            board[x][y] = 'X';
        } else if (board[x][y] == 'E') {
            // empty square has been clicked
            int numAdjacentMine = adjacentMine(board, x, y);
            if (numAdjacentMine == 0) {
                revealAllEmptyBlocks(board, x, y);
            } else {
                board[x][y] = (char) ('0' + numAdjacentMine);
            }
        }
        return board;
    }

    private void revealAllEmptyBlocks(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length) return;
        if (y < 0 || y >= board[0].length) return;
        if (board[x][y] != 'E') return;

        int numAdjacentMine = adjacentMine(board, x, y);

        if (numAdjacentMine > 0) {
            board[x][y] = (char) ('0' + numAdjacentMine);
            return;
        } else {
            board[x][y] = 'B';
        }

        revealAllEmptyBlocks(board, x + 1, y);
        revealAllEmptyBlocks(board, x - 1, y);
        revealAllEmptyBlocks(board, x, y + 1);
        revealAllEmptyBlocks(board, x, y - 1);
        revealAllEmptyBlocks(board, x + 1, y - 1);
        revealAllEmptyBlocks(board, x - 1, y - 1);
        revealAllEmptyBlocks(board, x + 1, y + 1);
        revealAllEmptyBlocks(board, x - 1, y + 1);
    }

    private int adjacentMine(char[][] board, int x, int y) {
        int mines = 0;
        int rows = board.length;
        int cols = board[0].length;

        // right
        if (y < cols - 1 && board[x][y + 1] == 'M') {
            mines++;
        }
        // left
        if (y > 0 && board[x][y - 1] == 'M') {
            mines++;
        }
        // top
        if (x > 0 && board[x - 1][y] == 'M') {
            mines++;
        }
        //down
        if (x < rows - 1 && board[x + 1][y] == 'M') {
            mines++;
        }

        // top-right
        if (x > 0 && y < cols - 1 && board[x - 1][y + 1] == 'M') {
            mines++;
        }

        // top-left
        if (x > 0 && y > 0 && board[x - 1][y - 1] == 'M') {
            mines++;
        }

        // bottom-right
        if (x < (rows - 1) && y < cols - 1 && board[x + 1][y + 1] == 'M') {
            mines++;
        }

        // bottom-left
        if (x < rows - 1 && y > 0 && board[x + 1][y - 1] == 'M') {
            mines++;
        }

        return mines;
    }
}
