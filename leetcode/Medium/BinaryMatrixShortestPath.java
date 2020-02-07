import input.TwoDArrayReader;
import java.io.IOException;
import java.util.*;

// 1091 https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class BinaryMatrixShortestPath {
    public static void main(String[] args) throws IOException {
        BinaryMatrixShortestPath obj = new BinaryMatrixShortestPath();
        int[][] grid = new TwoDArrayReader().get2DArray();
        System.out.println(obj.shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return -1;
        int cols = grid[0].length;
        if (cols == 0) return -1;
        if(grid[0][0] == 1) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int distance = 1;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        boolean[][] visited = new boolean[rows][cols];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                if (x == rows - 1 && y == cols - 1) return distance;

                for (int[] dir : dirs) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            distance++;
        }

        return -1;
    }
}
