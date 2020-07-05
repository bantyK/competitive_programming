//1504 https://leetcode.com/problems/count-submatrices-with-all-ones/
public class SubmatricesWithAllOnes {
    public static void main(String[] args) {
        SubmatricesWithAllOnes obj = new SubmatricesWithAllOnes();

        System.out.println(obj.numSubmat(new int[][]{
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        }));

        System.out.println(obj.numSubmat(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));

        System.out.println(obj.numSubmat(new int[][]{
                {1, 1, 1, 1, 1, 1}
        }));

        System.out.println(obj.numSubmat(new int[][]{
                {1, 0, 1}, {0, 1, 0}, {1, 0, 1}
        }));


    }


    public int numSubmat(int[][] mat) {
        int res = 0;
        int rows = mat.length;
        if (rows == 0) return res;
        int cols = mat[0].length;


        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1) {
                    res += countSubMatricesForRowAndCol(mat, rows, cols, r, c);
                }
            }
        }

        return res;
    }

    private int countSubMatricesForRowAndCol(int[][] mat, int rows, int cols, int row, int col) {
        int res = 0;
        int minCol = cols;
        for (int r = row; r < rows; r++) {
            for (int c = col; c < minCol; c++) {
                if (mat[r][c] == 1) {
                    res += 1;
                } else {
                    minCol = c;
                    break;
                }
            }
        }
        return res;
    }
}
