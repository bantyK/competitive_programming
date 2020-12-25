import java.util.Arrays;

//498 https://leetcode.com/problems/diagonal-traverse/
public class DiagonalTraversal {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6,7,8}, {9,10,11,12}};
        System.out.println(Arrays.toString(new DiagonalTraversal().findDiagonalOrder(matrix)));
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] res = new int[rows * cols];

        int row = 0;
        int col = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[row][col];
            if ((row + col) % 2 == 0) {
                // moving up
                if (col == cols - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                // moving down
                if (row == rows - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }

        return res;
    }
}
