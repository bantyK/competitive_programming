import input.TwoDArrayReader;

import java.util.Arrays;
import java.util.PriorityQueue;

// 1368 https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/

public class MinCostForOneValidPath {

    public static void main(String[] args) {
        MinCostForOneValidPath obj = new MinCostForOneValidPath();
        int[][] grid = new TwoDArrayReader().get2DArray();
        System.out.println(obj.minCostDjikstra(grid));
    }

    // Dijkstra's
    public int minCostDjikstra(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];

        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        distance[0][0] = 0;

        int[][] directions = new int[][]{{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // {x, y, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int cost = curr[2];

            for (int i = 1; i < directions.length; i++) {
                int newRow = x + directions[i][0];
                int newCol = y + directions[i][1];

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) continue;

                int newCost = cost;

                if (i != grid[x][y]) newCost += 1;

                if (distance[newRow][newCol] > newCost) {
                    distance[newRow][newCol] = newCost;
                    pq.offer(new int[]{newRow, newCol, newCost});
                }
            }
        }

        return distance[rows - 1][cols - 1];
    }

    // BFS solution
    public int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // directions values are given 1(right), 2(left), 3(down) and 4(up)
        // Since there is no value for 0, hence it is kept as empty

        //Now,  1 means go left. So we are at a cell and its value is equal to 1, then when we go its right cell,
        // there wont be any cost.
        // Simiarly, when we are at a cell with value 4. Then when we go to the cell below this cell, there wont be any cost

        // Hence these values are taken exactly as given in the questions
        int[][] directions = new int[][]{{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        //{x, y , cost}
        // will return the node with lowest cost
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[rows][cols];
        minHeap.add(new int[]{0, 0, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int x = curr[0];
            int y = curr[1];
            int cost = curr[2];

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            if (x == rows - 1 && y == cols - 1) {
                return cost;
            }

            for (int i = 1; i < directions.length; i++) {
                int newX = x + directions[i][0];
                int newY = y + directions[i][1];
                if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || visited[newX][newY]) continue;

                if (i == grid[x][y]) {
                    // we are comparing the direction with the current cell, because the current cell will decide what is the cost when
                    // we go to the next cell.

                    // If the value of this grid matches with the direction, no cost will be added

                    minHeap.offer(new int[]{newX, newY, cost});
                } else {
                    minHeap.offer(new int[]{newX, newY, cost + 1});
                }

            }
        }

        return -1;
    }


}