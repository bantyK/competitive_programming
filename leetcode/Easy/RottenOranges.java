package solutions.easy;

import java.util.*;


//994:  https://leetcode.com/problems/rotting-oranges/
public class RottenOranges {
    public static void main(String[] args) {
        RottenOranges obj = new RottenOranges();

        System.out.println(
                obj.orangesRotting(new int[][]{
                        {1, 1, 2, 0, 2, 0},
                })
        );
    }

    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }

                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0;

        int[][] directions = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            ++count;
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : directions) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];


                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0 || grid[x][y] == 2)
                        continue;

                    grid[x][y] = 2;
                    queue.offer(new int[]{x, y});
                    fresh--;
                }
            }
        }

        return fresh == 0 ? count - 1 : -1;

    }

}
