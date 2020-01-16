import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 1293 https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
public class ShortestPathObstacleSolution {
    public static void main(String[] args) throws IOException {
        ShortestPathObstacleSolution obj = new ShortestPathObstacleSolution();
        final int[][] grid = new TwoDArrayReader().get2DArray();

        System.out.println(obj.shortestPath(grid, 1));
    }

    /**
     * Actual solution
     * @param grid
     * @param k
     * @return
     */
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,0,0}); // steps taken, i, j, obstacles so far
        Set<String> visited = new HashSet<>();
        visited.add(0 + "" + 0 + "" + 0);
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int steps = curr[0];
            int i = curr[1];
            int j = curr[2];
            int obstacleCount = curr[3];

            if(i == rows - 1 && j == cols -1) {
                return steps;
            }

            for(int[] dir : directions) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                if(newI >= 0 && newI < rows && newJ >= 0 && newJ < cols) {
                    int newObstacleCount = obstacleCount + grid[newI][newJ];
                    String key = newI + "" + newJ + "" + newObstacleCount;
                    if(newObstacleCount <= k  && !visited.contains(key)) {
                        queue.offer(new int[]{steps+1, newI, newJ, newObstacleCount});
                        visited.add(key);
                    }
                }
            }
        }

        return -1;
    }


    /**
     * No obstacles
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        return helper(m, n, 0, 0);
    }

    private int helper(int rows, int cols, int i, int j) {
        if (i < 0 || i >= rows) return 0;
        if (j < 0 || j >= cols) return 0;
        if (i == rows - 1 && j == cols - 1) return 1;

        return helper(rows, cols, i + 1, j) + helper(rows, cols, i, j + 1);
    }

    /**
     * Total number of unique paths with obstacles.
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathWithObstacles(int[][] obstacleGrid) {
        Integer[][] dp = new Integer[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePaths(obstacleGrid, 0, 0, dp);
    }

    private int uniquePaths(int[][] obstacleGrid, int i, int j, Integer[][] dp) {
        if (i < 0 || i >= obstacleGrid.length) return 0;
        if (j < 0 || j >= obstacleGrid[i].length) return 0;
        if (obstacleGrid[i][j] == 1) return 0;
        if (i == obstacleGrid.length - 1 && j == obstacleGrid[i].length - 1) return 1;
        if (dp[i][j] != null) return dp[i][j];

        int paths = uniquePaths(obstacleGrid, i + 1, j, dp) + uniquePaths(obstacleGrid, i, j + 1, dp);
        dp[i][j] = paths;
        return paths;
    }


}
