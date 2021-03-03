import input.TwoDArrayReader;

import java.util.PriorityQueue;

// 778 https://leetcode.com/problems/swim-in-rising-water/
public class SwimInRisingWater {

    public static void main(String[] args) {
        SwimInRisingWater obj = new SwimInRisingWater();
        int[][] grid = new TwoDArrayReader().get2DArray();
        System.out.println(obj.swimInWater(new int[][]{{0, 2}, {1, 3}}));
    }

    //Binary Search
    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int low = 0;
        int high = 50 * 50; // max value according to question

        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[rows][cols];
            valid(mid, 0, 0, grid, visited);
            if (visited[rows - 1][cols - 1]) {
                // we can reach to the last cell, considering mid as the maximum time.
                // try to minimise this
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean valid(int maxTime, int r, int c, int[][] grid, boolean[][] visited) {
        if (r < 0 || r >= grid.length) return false;
        if (c < 0 || c >= grid[0].length) return false;
        if (visited[r][c] || grid[r][c] > maxTime) return false;

        visited[r][c] = true;

        return valid(maxTime, r + 1, c, grid, visited)
                || valid(maxTime, r - 1, c, grid, visited)
                || valid(maxTime, r, c + 1, grid, visited)
                || valid(maxTime, r, c - 1, grid, visited);
    }


    // Dijstra's solution
    public int swimInWaterDijsktra(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // {x, y, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, grid[0][0]});

        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];

            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newy = y + dir[1];

                if (newX < 0 || newX >= rows || newy < 0 || newy >= cols) continue;

                if (!visited[newX][newy]) {
                    visited[newX][newy] = true;
                    // if the height of the next cell(nx, ny) is lower than the current cell (x,y)
                    // then there won't be any addition in the time, because we dont need any time
                    // to move between cells of same height. Hence the time remains same as current cell

                    // However, if the height of the next cell is greater than current cell, then we
                    // have to take that height, because we have to wait atleast for that(grid[nx][ny]) amount of time

                    // taking a max of current cell and next cell takes care of both these conditions
                    int time = Math.max(curr[2], grid[newX][newy]);
                    if (newX == rows - 1 && newy == cols - 1) {
                        return time;
                    }

                    pq.offer(new int[]{newX, newy, time});
                }
            }

        }
        return 0; // should not come here if the input is correct
    }
}