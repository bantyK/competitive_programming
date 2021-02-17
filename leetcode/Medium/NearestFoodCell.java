import java.util.*;

//1730 https://leetcode.com/problems/shortest-path-to-get-food/
public class NearestFoodCell {
    public static void main(String[] args) {
        NearestFoodCell obj = new NearestFoodCell();
        char[][] grid = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', '*', 'O', 'O', 'O', 'X' },
                { 'X', 'O', 'O', '#', 'O', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X' } };
        System.out.println(obj.getFood(grid));

    }

    public int getFood(char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            boolean found = false;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '*') {
                    queue.offer(new int[] { i, j, 1 });
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }

        int[][] directions = new int[][] { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
        while (!queue.isEmpty()) {
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                int[] curr = queue.poll();

                for (int[] dir : directions) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];

                    if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols || grid[newRow][newCol] == 'X') {
                        continue;
                    }

                    if (grid[newRow][newCol] == '#') {
                        return curr[2];
                    }

                    queue.offer(new int[] { newRow, newCol, curr[2] + 1 });
                    grid[newRow][newCol] = 'X';
                }
            }
        }

        return -1;
    }
}