import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//417 https://leetcode.com/problems/pacific-atlantic-water-flow/
public class PacificAtlantic {
    public static void main(String[] args) throws IOException {
        PacificAtlantic obj = new PacificAtlantic();
        int[][] matrix = new TwoDArrayReader().get2DArray();
        List<List<Integer>> lists = obj.pacificAtlantic(matrix);
        for (List l : lists) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) return Collections.emptyList();

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] pacificRegions = new boolean[rows][cols];
        boolean[][] atlanticRegions = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            // pacific
            floodFill(matrix, pacificRegions, Integer.MIN_VALUE, row, 0);

            // atlantic
            floodFill(matrix, atlanticRegions, Integer.MIN_VALUE, row, cols - 1);
        }


        for (int col = 0; col < cols; col++) {
            // pacific
            floodFill(matrix, pacificRegions, Integer.MIN_VALUE, 0, col);

            // atlantic
            floodFill(matrix, atlanticRegions, Integer.MIN_VALUE, rows - 1, col);
        }

        List<List<Integer>> commonRegions = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (atlanticRegions[i][j] && pacificRegions[i][j]) {
                    commonRegions.add(Arrays.asList(i, j));
                }
            }
        }
        return commonRegions;
    }

    private void floodFill(int[][] matrix, boolean[][] region, int prevValue, int x, int y) {
        if (x < 0 || x >= matrix.length) return;
        if (y < 0 || y >= matrix[x].length) return;
        if (region[x][y]) return;
        if (matrix[x][y] < prevValue) return;

        region[x][y] = true;

        floodFill(matrix, region, matrix[x][y], x + 1, y);
        floodFill(matrix, region, matrix[x][y], x - 1, y);
        floodFill(matrix, region, matrix[x][y], x, y - 1);
        floodFill(matrix, region, matrix[x][y], x, y + 1);
    }
}
