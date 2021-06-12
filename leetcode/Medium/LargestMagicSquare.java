// 1895 https://leetcode.com/problems/largest-magic-square/
public class LargestMagicSquare {
    public int largestMagicSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        int[][] rowsSum = new int[rows + 1][cols + 1];
        int[][] colsSum = new int[rows + 1][cols + 1];

        // presum row-wise
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rowsSum[r + 1][c + 1] = rowsSum[r + 1][c] + grid[r][c];
            }
        }

        // presum col-wise
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                colsSum[r + 1][c + 1] = colsSum[r][c + 1] + grid[r][c];
            }
        }


        int maxSize = Math.min(rows, cols);

        // check all squares of these sizes
        int res = 1;

        for (int size = 2; size <= maxSize; size++) {
            for (int r1 = 1; r1 <= rows - size + 1; r1++) {
                int r2 = r1 + size - 1;
                for (int c1 = 1; c1 <= cols - size + 1; c1++) {
                    int c2 = c1 + size - 1;
                    // all rows, cols and diagonals should be equal to this value
                    long expected = rowsSum[r1][c2] - rowsSum[r1][c1 - 1];

                    boolean ok = true; // flag used to break early in case of any mismatch

                    // rows
                    for (int r = r1 + 1; r <= r2; r++) {
                        long rS = rowsSum[r][c2] - rowsSum[r][c1 - 1];
                        if (rS != expected) {
                            ok = false;
                            break;
                        }
                    }

                    if (!ok) {
                        // no need to check rest
                        continue;
                    }
                    // cols
                    for (int c = c1; c <= c2; c++) {
                        long cS = colsSum[r2][c] - colsSum[r1 - 1][c];
                        if (cS != expected) {
                            ok = false;
                            break;
                        }
                    }

                    // upto this point, rows and cols are ok, check diagonals now

                    if (ok) {
                        // topLeft to bottomRight diagonal
                        long diag1 = 0;
                        int r = r1 - 1;
                        int c = c1 - 1;
                        while (r < r2) {
                            diag1 += grid[r][c];
                            r++;
                            c++;
                        }
                        if (diag1 != expected) {
                            continue;
                        }


                        // topRight to bottomLeft diagonal
                        long diag2 = 0;
                        r = r1 - 1;
                        c = c2 - 1;

                        while (r < r2) {
                            diag2 += grid[r][c];
                            r++;
                            c--;
                        }


                        if (diag2 != expected) {
                            continue;
                        }
                    }

                    // everything is all right, rows, cols and diagonals are all equal(equal to expected, defined above)
                    if (ok) {
                        res = Math.max(res, size);
                    }
                }
            }
        }

        return res;
    }
}