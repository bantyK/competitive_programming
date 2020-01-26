import input.TwoDArrayReader;
import input.Util;

import java.io.IOException;
import java.util.*;

// 542 https://leetcode.com/problems/01-matrix/
public class Matrix01 {
    public static void main(String[] args) throws IOException {
        Matrix01 obj = new Matrix01();
        final int[][] array = new TwoDArrayReader().get2DArray();
        obj.updateMatrix(array);

        Util.print2dArray(array);
    }

    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return new int[0][0];

        int cols = matrix[0].length;
        if (cols == 0) return new int[0][0];
        Queue<int[]> queue = new ArrayDeque<>();


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) queue.offer(new int[]{i, j});
                else matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int[] dir : directions) {
                int i = curr[0] + dir[0];
                int j = curr[1] + dir[1];

                if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= matrix[x][y] + 1)
                    continue;
                queue.offer(new int[]{i, j});
                matrix[i][j] = 1 + matrix[x][y];
            }
        }

        return matrix;
    }


}
