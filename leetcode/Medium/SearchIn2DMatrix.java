import input.TwoDArrayReader;

import java.io.IOException;
import java.util.Arrays;

// 74 https://leetcode.com/problems/search-a-2d-matrix/
public class SearchIn2DMatrix {
    public static void main(String[] args) throws IOException {
        SearchIn2DMatrix obj = new SearchIn2DMatrix();
        int[][] matrix = {{1},{3}};//new TwoDArrayReader().get2DArray();
        int target = 3;
        System.out.println(obj.searchMatrix(matrix, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;

        int cols = matrix[0].length;

        if (cols == 0 || matrix[0][0] > target) return false;

        int row = 0;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] > target) {
                row = i;
                break;
            }
        }

        row = row > 0 ? row - 1 : row;

        final int index = Arrays.binarySearch(matrix[row], target);
        return index >= 0;
    }
}
