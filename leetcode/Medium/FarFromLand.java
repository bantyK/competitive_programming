import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//1162 https://leetcode.com/problems/as-far-from-land-as-possible/
public class FarFromLand {

    public static void main(String[] args) throws IOException {
        FarFromLand obj = new FarFromLand();
        int i = obj.maxDistance(new TwoDArrayReader().get2DArray());
        System.out.println(i);
    }

    public int maxDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        if (queue.size() == 0 || queue.size() == rows * cols) return -1;

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] start = queue.poll();
            int x = start[0];
            int y = start[1];
            for (int[] dir : directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && distance[nextX][nextY] > distance[x][y] + 1) {
                    distance[nextX][nextY] = distance[x][y] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                max = Math.max(distance[i][j], max);
            }
        }

        return max;
    }


}
