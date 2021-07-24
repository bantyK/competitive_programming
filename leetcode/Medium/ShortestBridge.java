import java.util.*;

// 934 https://leetcode.com/problems/shortest-bridge/
public class ShortestBridge {
    public static void main(String[] args) {

    }

    public int shortestBridge(int[][] grid) {

        // first find anyone of the island, the shortest bridge between this island could start with any cell,
        // so add all the cells of this island into a queue. We will consider all the cells for a BFS to another cell of second
        // island.

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;

        for (int i = 0; i < rows; i++) {
            if (found) break;

            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    found = true;
                    dfs(grid, visited, queue, i, j);
                    break;
                }
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // BFS to expand this island and reach island 2. BFS will give the shortest path between island1 and island2
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < rows && y < rows && !visited[x][y]) {
                        if (grid[x][y] == 1) {
                            return distance;
                        }
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            distance++;
        }

        return -1;
    }

    private void dfs(int[][] grid, boolean[][] visited, Queue<int[]> queue, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        dfs(grid, visited, queue, r + 1, c);
        dfs(grid, visited, queue, r - 1, c);
        dfs(grid, visited, queue, r, c + 1);
        dfs(grid, visited, queue, r, c - 1);
    }

}