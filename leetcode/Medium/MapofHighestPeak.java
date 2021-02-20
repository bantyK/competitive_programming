import java.util.LinkedList;
import java.util.Queue;

//1765 https://leetcode.com/problems/map-of-highest-peak/
public class MapofHighestPeak {

    public int[][] highestPeak(int[][] isWater) {
        int rows = isWater.length;
        int cols = isWater[0].length;

        int[][] res = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    res[i][j] = -1;
                }
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();

                int r = curr[0];
                int c = curr[1];

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];


                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || res[newRow][newCol] != -1) {
                        continue;
                    }
                    res[newRow][newCol] = res[r][c] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return res;
    }
}
