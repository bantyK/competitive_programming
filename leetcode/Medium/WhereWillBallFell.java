//1706 https://leetcode.com/problems/where-will-the-ball-fall/
public class WhereWillBallFell {

    public int[] findBall(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] res = new int[cols];
        for(int c = 0; c < cols; c++) {
            res[c] = dfs(grid, 0, c);
        }
        return res;
    }

    private int dfs(int[][] grid, int r, int c) {
        if(r == grid.length) return c;
        if(isValidCell(grid, r, c)) {
            // the ball can only go down with the adjacent has same diagonals
            if(grid[r][c] == 1) {
                // (r,c) = '\' and (r, c+1) should also be '\', otherwise the ball is stuck
                if(isValidCell(grid, r, c + 1) && grid[r][c+1] == 1) {
                    return dfs(grid, r + 1, c + 1);
                }
            } else {
                // (r,c) = '/' and (r, c-1) should also be '/', otherwise the ball is stuck
                if(isValidCell(grid, r, c - 1) && grid[r][c - 1] == -1) {
                    return dfs(grid, r + 1, c - 1);
                }
            }
        }

        return -1;
    }

    private boolean isValidCell(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
