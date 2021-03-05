import input.Util;

//1034 https://leetcode.com/problems/coloring-a-border/

public class BorderColoring {


    int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        BorderColoring obj = new BorderColoring();
        int[][] grid = new int[][]{{4, 6, 8, 3, 7}, {1, 2, 2, 2, 4}, {3, 2, 2, 2, 1}, {3, 2, 2, 2, 7}, {4, 6, 4, 5, 8}};

        Util.print2dArray(grid);
        System.out.println();
        int[][] res = obj.colorBorder(grid, 2, 2, 9);
        Util.print2dArray(res);
    }


    // This solution is based on the idea that as soon as we move out of the connected
    // component, that cell is the boundary of the connected component,
    // change the color of that cell

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int r = grid.length;
        int c = grid[0].length;

        boolean[][] visited = new boolean[r][c];
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            System.arraycopy(grid[i], 0, res[i], 0, c);
        }
        dfs(grid, r0, c0, grid[r0][c0], color, visited, res);
        return res;
    }

    // Approach 2

    // cannot change the grid value, otherwise later we might get the wrong values of cells for internal cells
    private boolean dfs(int[][] grid, int r, int c, int val, int color, boolean[][] visited, int[][] res) {
        if (r < 0 || r >= grid.length) return false;
        if (c < 0 || c >= grid[0].length) return false;
        if (grid[r][c] != val) {
            return false;
        }
        if (visited[r][c]) return true;

        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int newR = r + directions[i][0];
            int newC = c + directions[i][1];

            if (!dfs(grid, newR, newC, val, color, visited, res)) {
                if (grid[r][c] == val) {
                    res[r][c] = color;
                }
            }
        }

        return true;

    }

    public int[][] colorBorder2(int[][] grid, int r0, int c0, int color) {
        dfs2(grid, r0, c0, grid[r0][c0]);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    // these cells are marked by dfs as boundary cells and are part of the connected component
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    private void dfs2(int[][] grid, int r, int c, int oldColor) {
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (grid[r][c] < 0 || grid[r][c] != oldColor) return;

        grid[r][c] = -grid[r][c]; // using this trick to avoid using visited matrix

        for (int[] dir : dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            dfs2(grid, newR, newC, oldColor);
        }

        if (r > 0 && c > 0 && r < grid.length - 1 && c < grid[0].length - 1
                && Math.abs(grid[r + 1][c]) == oldColor
                && Math.abs(grid[r - 1][c]) == oldColor
                && Math.abs(grid[r][c + 1]) == oldColor
                && Math.abs(grid[r][c - 1]) == oldColor
        ) {
            // this is an internal cell, all the adjacent neighbours of this cell is of same color, so its color wont change
            grid[r][c] = oldColor;
        }
    }

}
