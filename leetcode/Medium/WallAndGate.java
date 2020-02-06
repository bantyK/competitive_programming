import input.Util;

import java.util.*;

// 286 https://leetcode.com/problems/walls-and-gates/
public class WallAndGate {
    private static final int INF = Integer.MAX_VALUE;
    int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        WallAndGate obj = new WallAndGate();

        int[][] rooms = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}};

        obj.findShortestPathToGates(rooms);
        Util.print2dArray(rooms);

    }

    public void findShortestPathToGates(int[][] maze) {
        int rows = maze.length;
        if (rows == 0) return;
        int cols = maze[0].length;
        if (cols == 0) return;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 0) {
                    // do not run both at the same time
                    dfs(maze, i, j, 0);
//                     bfs(maze, i, j);
                }
            }
        }
    }

    private void bfs(int[][] maze, int i, int j) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            for (int[] dir : directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && maze[nextX][nextY] > 0 && maze[nextX][nextY] > maze[x][y] + 1) {
                    int newDistance = maze[x][y] + 1;
                    maze[nextX][nextY] = Math.min(maze[nextX][nextY], newDistance);
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    private void dfs(int[][] maze, int i, int j, int distance) {
        if (i < 0 || i >= maze.length) return;
        if (j < 0 || j >= maze[i].length) return;
        if (maze[i][j] == -1 || maze[i][j] < distance) return;

        maze[i][j] = distance;

        dfs(maze, i + 1, j, distance + 1);
        dfs(maze, i - 1, j, distance + 1);
        dfs(maze, i, j + 1, distance + 1);
        dfs(maze, i, j - 1, distance + 1);
    }

}
