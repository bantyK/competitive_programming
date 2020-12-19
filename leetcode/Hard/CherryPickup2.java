import input.TwoDArrayReader;

// 1463 https://leetcode.com/problems/cherry-pickup-ii/
public class CherryPickup2 {
    public static void main(String[] args) {
        System.out.println(new CherryPickup2().cherryPickup(new int[][]{{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}}));

        int[][] dArray = new TwoDArrayReader().get2DArray();
        System.out.println(new CherryPickup2().cherryPickup(dArray));
    }

    public int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Integer[][][][] dp = new Integer[rows][cols][rows][cols];

        int maxCherries = explore(grid, 0, 0, 0, cols - 1, dp);
        return Math.max(0, maxCherries);
    }

    private int explore(int[][] grid, int r1, int c1, int r2, int c2, Integer[][][][] dp) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r1 >= rows || r2 >= rows || c1 < 0 || c2 < 0 || c1 >= cols || c2 >= cols) {
            return Integer.MIN_VALUE;
        }

        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (dp[r1][c1][r2][c2] != null) return dp[r1][c1][r2][c2];

        int cherries = grid[r1][c1];
        if (r1 != r2 || c1 != c2) {
            cherries += grid[r2][c2];
        }

        if (r1 == rows - 1) {
            return cherries;
        }

        int ans = Integer.MIN_VALUE;

        ans = Math.max(ans, explore(grid, r1 + 1, c1, r2 + 1, c2, dp)); // {down, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1, r2 + 1, c2 - 1, dp)); // {down, down-left}
        ans = Math.max(ans, explore(grid, r1 + 1, c1, r2 + 1, c2 + 1, dp)); /// {down, down-right}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, r2 + 1, c2, dp)); // {down-left, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, r2 + 1, c2, dp)); // {down-right, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, r2 + 1, c2 - 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, r2 + 1, c2 + 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, r2 + 1, c2 + 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, r2 + 1, c2 - 1, dp));

        dp[r1][c1][r2][c2] = cherries + ans;
        return dp[r1][c1][r2][c2];
    }


    // reduce one level of DP
    // Almost similar CherryPickup 1. Look at its solution and explaination for detailed analysis
    public int cherryPickup2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Integer[][][] dp = new Integer[rows][cols][cols];

        int maxCherries = explore(grid, 0, 0, cols - 1, dp);
        return Math.max(0, maxCherries);
    }

    private int explore(int[][] grid, int r1, int c1, int c2, Integer[][][] dp) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r1 >= rows || r1 >= rows || c1 < 0 || c2 < 0 || c1 >= cols || c2 >= cols) {
            return Integer.MIN_VALUE;
        }

        if (grid[r1][c1] == -1 || grid[r1][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (dp[r1][c1][c2] != null) return dp[r1][c1][c2];

        int cherries = grid[r1][c1];
        if (c1 != c2) {
            cherries += grid[r1][c2];
        }

        if (r1 == rows - 1) {
            return cherries;
        }

        int ans = Integer.MIN_VALUE;

        ans = Math.max(ans, explore(grid, r1 + 1, c1, c2, dp)); // {down, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1, c2 - 1, dp)); // {down, down-left}
        ans = Math.max(ans, explore(grid, r1 + 1, c1, c2 + 1, dp)); /// {down, down-right}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, c2, dp)); // {down-left, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, c2, dp)); // {down-right, down}
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, c2 - 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 - 1, c2 + 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, c2 + 1, dp));
        ans = Math.max(ans, explore(grid, r1 + 1, c1 + 1, c2 - 1, dp));

        dp[r1][c1][c2] = cherries + ans;
        return dp[r1][c1][c2];
    }


}