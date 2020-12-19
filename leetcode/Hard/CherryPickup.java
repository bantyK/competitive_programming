// 741 https://leetcode.com/problems/cherry-pickup/
public class CherryPickup {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
//        System.out.println(new CherryPickup().cherryPickup(grid));

        int[][] grid2 = new int[][]{{1, 1, -1}, {1, -1, 1}, {-1, 1, 1}};
//        System.out.println(new CherryPickup().cherryPickup(grid2));

        int[][] grid3 = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(new CherryPickup().cherryPickup(grid3));
    }

    public int cherryPickup(int[][] grid) {
        // 4 dimensional dp
        // instead of considering 1 person moving from (0,0) to (n-1, n-1) and back from (n-1, n-1) to (0,0),
        // consider that there are two person who are moving from (0,0) to (n-1, n-1) because it is essentially the same thing

        // the state of the dp will be the position of these two person, which can be established by the co-ordinates of 2 person
        // {r1, c1, r2, c2}
        // the 4 dimensional DP can further be reduced to 3-D which is done in the function below
        int rows = grid.length;
        int cols = grid[0].length;
        Integer[][][][] dp = new Integer[rows][cols][rows][cols];

        int maxCherries = explore(grid, 0, 0, 0, 0, dp);
        return Math.max(maxCherries, 0);
    }

    private int explore(int[][] grid, int r1, int c1, int r2, int c2, Integer[][][][] dp) {

        int N = grid.length;

        if (r1 >= N || r2 >= N || c1 >= N || c2 >= N) {
            return Integer.MIN_VALUE;
        }

//        System.out.println("r1: " + r1 + ", r2: " + r2 + ", c1 : " + c1 + ", c2 : " + c2);
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (r1 == N - 1 && c1 == N - 1) {
            // Since its a square matrix and both person moves one step at a time
            // they will reach the end matrix at the same time
            // also one of those will be picking the cherries, returning the value once will be enough.
            return grid[N - 1][N - 1];
        }

        if (r2 == N - 1 && c2 == N - 1) {
            // will never be executed
            // System.out.println("Executed");
            return grid[N - 1][N - 1];
        }

        if (dp[r1][c1][r2][c2] != null) return dp[r1][c1][r2][c2];


        int cherries = grid[r1][c1];

        if (r1 != r2 || c1 != c2) {
            // both person cannot pick cherries from the same cell
            cherries += grid[r2][c2];
        }

        /**
         * both person can move independently to bottom or right directions
         * 4 possible directions are possible;
         * right, right (r1, c1 + 1, r2, c2 + 1)
         * right, down (r1, c1 + 1, r2 + 1, c2)
         * down, right (r1 + 1, c1, r2, c2 + 1)
         * down, down (r1 + 1, c1, r2 + 1, c2)
         *
         * Out of all these 4 transitions, the one which will give the max will be our answer
         */

        int val1 = explore(grid, r1, c1 + 1, r2, c2 + 1, dp);
        int val2 = explore(grid, r1, c1 + 1, r2 + 1, c2, dp);
        int val3 = explore(grid, r1 + 1, c1, r2, c2 + 1, dp);
        int val4 = explore(grid, r1 + 1, c1, r2 + 1, c2, dp);

        dp[r1][c1][r2][c2] = cherries + Math.max(Math.max(val1, val2), Math.max(val3, val4));
        return dp[r1][c1][r2][c2];
    }


    // Same algo, with reduced space complexity for DP table
    // since both person will be equidistant from origin, r1 + c1 == r2 + c2 (always)
    // from this equation, if three variables are present, 4th can be calculated
    // this helps reducing the dp from 4D to 3D
    public int cherryPickup2(int[][] grid) {
        // 3 dimensional dp solution
        // instead of considering 1 person moving from (0,0) to (n-1, n-1) and back from (n-1, n-1) to (0,0),
        // consider that there are two person who are moving from (0,0) to (n-1, n-1) because it is essentially the same thing

        // the state of the dp will be the position of these two person, which can be established by the co-ordinates of 2 person
        // {r1, c1, r2, c2}
        // the 4 dimensional DP can further be reduced to 3-D which is done in the function below
        int rows = grid.length;
        int cols = grid[0].length;
        Integer[][][] dp = new Integer[rows][cols][cols];

        int maxCherries = explore(grid, 0, 0, 0, dp);
        return Math.max(maxCherries, 0);
    }

    private int explore(int[][] grid, int r1, int c1, int c2, Integer[][][] dp) {

        int N = grid.length;

        int r2 = r1 + c1 - c2;

        if (r1 >= N || r2 >= N || c1 >= N || c2 >= N) {
            return Integer.MIN_VALUE;
        }

//        System.out.println("r1: " + r1 + ", r2: " + r2 + ", c1 : " + c1 + ", c2 : " + c2);
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (r1 == N - 1 && c1 == N - 1) {
            // Since its a square matrix and both person moves one step at a time
            // they will reach the end matrix at the same time
            // also one of those will be picking the cherries, returning the value once will be enough.
            return grid[N - 1][N - 1];
        }

        if (r2 == N - 1 && c2 == N - 1) {
            // will never be executed
            // System.out.println("Executed");
            return grid[N - 1][N - 1];
        }

        if (dp[r1][c1][c2] != null) return dp[r1][c1][c2];


        int cherries = grid[r1][c1];

        if (r1 != r2 || c1 != c2) {
            // both person cannot pick cherries from the same cell
            cherries += grid[r2][c2];
        }

        /**
         * both person can move independently to bottom or right directions
         * 4 possible directions are possible;
         * right, right (r1, c1 + 1, r2, c2 + 1)
         * right, down (r1, c1 + 1, r2 + 1, c2)
         * down, right (r1 + 1, c1, r2, c2 + 1)
         * down, down (r1 + 1, c1, r2 + 1, c2)
         *
         * Out of all these 4 transitions, the one which will give the max will be our answer
         */

        int val1 = explore(grid, r1, c1 + 1, c2 + 1, dp);
        int val2 = explore(grid, r1, c1 + 1, c2, dp);
        int val3 = explore(grid, r1 + 1, c1, c2 + 1, dp);
        int val4 = explore(grid, r1 + 1, c1, c2, dp);

        dp[r1][c1][c2] = cherries + Math.max(Math.max(val1, val2), Math.max(val3, val4));
        return dp[r1][c1][c2];
    }

}