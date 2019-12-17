import java.util.*;

// 959 https://leetcode.com/problems/regions-cut-by-slashes/
public class RegionCutSlashes {
    public static void main(String[] args) {
        RegionCutSlashes obj = new RegionCutSlashes();
        String[] grid = new String[]{
                "//",
                "/ "
        };
        int res = obj.regionsBySlashes(grid);
        System.out.println(res);
    }

    public int regionsBySlashes(String[] grid) {
        if (grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length();
        int[][] matrix = new int[3 * rows][3 * cols];

        for (int i = 0; i < grid.length; i++) {
            String s = grid[i];
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '/') {
                    matrix[i * 3][(j * 3) + 2] = 1;
                    matrix[(i * 3) + 1][(j * 3) + 1] = 1;
                    matrix[(i * 3) + 2][j * 3] = 1;
                } else if (c == '\\') {
                    matrix[i * 3][(j * 3)] = 1;
                    matrix[(i * 3) + 1][(j * 3) + 1] = 1;
                    matrix[(i * 3) + 2][(j * 3) + 2] = 1;
                }
            }
        }

        int area = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                area += dfs(matrix, i, j);
            }
        }

        return area;
    }

    private int dfs(int[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length) return 0;
        if (col < 0 || col >= matrix[0].length) return 0;
        if (matrix[row][col] == 1) return 0;

        int count = 1;

        matrix[row][col] = 1;

        dfs(matrix, row + 1, col);
        dfs(matrix, row - 1, col);
        dfs(matrix, row, col + 1);
        dfs(matrix, row, col - 1);

        return count;
    }

    private void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
            }

            System.out.println();
        }
    }


}
