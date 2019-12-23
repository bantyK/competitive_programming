import input.TwoDArrayReader;

import java.io.IOException;

// 1254 https://leetcode.com/problems/number-of-closed-islands/
public class ClosedIslands {
    public static void main(String[] args) throws IOException {
        ClosedIslands obj = new ClosedIslands();
        int[][] grid = new TwoDArrayReader().get2DArray();
        System.out.println(obj.closedIsland(grid));
    }

    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        // mark all boundary zeros to 2
        // top rows
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == 0) {
                dfs(grid, 0, col);
            }
        }

        // right column
        for (int row = 0; row < rows; row++) {
            if (grid[row][cols - 1] == 0) {
                dfs(grid, row, cols - 1);
            }
        }

        // bottom row
        for (int col = 0; col < cols; col++) {
            if (grid[rows - 1][col] == 0) {
                dfs(grid, rows - 1, col);
            }
        }

        // left column
        for (int row = 0; row < rows; row++) {
            if (grid[row][0] == 0) {
                dfs(grid, row, 0);
            }
        }
//        print(grid);

        int components = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    components += count(grid, i, j);
                }
            }
        }

        return components;

    }

    private int count(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) return 0;
        else if (j < 0 || j >= grid[i].length) return 0;
        else if (grid[i][j] == 1 || grid[i][j] == 2) return 0;

        int count = 1;
        grid[i][j] = 2;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);

        return count;

    }

    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) return;
        else if (j < 0 || j >= grid[i].length) return;
        else if (grid[i][j] == 1 || grid[i][j] == 2) return;

        grid[i][j] = 2;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);

    }

    private void print(int[][] mat) {
        for (int[] ints : mat) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
