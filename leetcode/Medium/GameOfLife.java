import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 289 https://leetcode.com/problems/game-of-life/
public class GameOfLife {
    public static void main(String[] args) throws IOException {
        GameOfLife obj = new GameOfLife();
        final int[][] grid = new TwoDArrayReader().get2DArray();
        obj.gameOfLife(grid);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void gameOfLife(int[][] board) {
        if(board.length == 0 || board[0].length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        int[][] result = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int live = numLive(board, i, j);
                if(board[i][j] == 0) {
                    if ( live == 3)
                        result[i][j] = 1;
                } else {
                    if(live == 2 || live == 3) {
                        result[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    private int numLive(int[][] board, int row, int col) {
        int live = 0;
        int rows = board.length;
        int cols = board[0].length;

        if(row < rows - 1)
            live += board[row + 1][col] == 1 ? 1 : 0;

        if(row > 0)
            live += board[row - 1][col] == 1 ? 1 : 0;

        if (col < cols - 1)
            live += board[row][col + 1] == 1 ? 1 : 0;

        if(col > 0)
            live += board[row][col - 1] == 1 ? 1 : 0;

        if(row < rows - 1 && col < cols - 1)
            live += board[row + 1][col + 1] == 1 ? 1 : 0;

        if(row < rows - 1 && col > 0)
            live += board[row + 1][col - 1] == 1 ? 1 : 0;

        if(row > 0 && col < cols - 1)
            live += board[row - 1][col + 1] == 1 ? 1 : 0;

        if(row > 0 && col > 0)
            live += board[row - 1][col - 1] == 1 ? 1 : 0;

        return live;
    }
}
