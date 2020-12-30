import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 289 https://leetcode.com/problems/game-of-life/
public class GameOfLife {
    public static void main(String[] args) throws IOException {
        GameOfLife obj = new GameOfLife();
        final int[][] grid = new TwoDArrayReader().get2DArray();
        obj.gameOfLife(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    ////////////////////// Constant space solution //////////////////////

    private final int ALIVE = 1; // currently this cell is alive
    final int DEAD = 0; // currently this cell is dead
    final int DEADTOALIVE = 2; // currently this cell is dead, but for final answer, this cell will be alive
    final int ALIVETODEAD = 3; // currently this cell is alive, but for final answer, this cell will be dead
    private static final int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 },
            { -1, 1 }, { 1, -1 } };

    public void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int aliveNeighbours = 0;

                for (int[] direction : directions) {
                    aliveNeighbours += isAlive(board, i + direction[0], j + direction[1]) ? 1 : 0;
                }

                if (board[i][j] == DEAD) {
                    if (aliveNeighbours == 3) {
                        board[i][j] = DEADTOALIVE;
                    }
                } else {
                    if (aliveNeighbours != 2 && aliveNeighbours != 3) {
                        board[i][j] = ALIVETODEAD;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == DEADTOALIVE) {
                    board[i][j] = ALIVE;
                } else if (board[i][j] == ALIVETODEAD) {
                    board[i][j] = DEAD;
                }
            }
        }

    }

    private boolean isAlive(int[][] board, int r, int c) {
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length
                && (board[r][c] == ALIVE || board[r][c] == ALIVETODEAD);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;

        int rows = board.length;
        int cols = board[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int live = numLive(board, i, j);
                if (board[i][j] == 0) {
                    if (live == 3)
                        result[i][j] = 1;
                } else {
                    if (live == 2 || live == 3) {
                        result[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    private int numLive(int[][] board, int row, int col) {
        int live = 0;
        int rows = board.length;
        int cols = board[0].length;

        if (row < rows - 1)
            live += board[row + 1][col] == 1 ? 1 : 0;

        if (row > 0)
            live += board[row - 1][col] == 1 ? 1 : 0;

        if (col < cols - 1)
            live += board[row][col + 1] == 1 ? 1 : 0;

        if (col > 0)
            live += board[row][col - 1] == 1 ? 1 : 0;

        if (row < rows - 1 && col < cols - 1)
            live += board[row + 1][col + 1] == 1 ? 1 : 0;

        if (row < rows - 1 && col > 0)
            live += board[row + 1][col - 1] == 1 ? 1 : 0;

        if (row > 0 && col < cols - 1)
            live += board[row - 1][col + 1] == 1 ? 1 : 0;

        if (row > 0 && col > 0)
            live += board[row - 1][col - 1] == 1 ? 1 : 0;

        return live;
    }
}
