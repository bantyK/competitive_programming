// 240 https://leetcode.com/problems/search-a-2d-matrix-ii/
public class SearchIn2DMatrix2 {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;

        int cols = matrix[0].length;
        if (cols == 0) return false;

        int row = rows - 1;
        int col = 0;

        while (row >= 0 && col < cols) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }

        return false;
    }

}
