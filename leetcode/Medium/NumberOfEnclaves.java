package dfs;

//1020 https://leetcode.com/problems/number-of-enclaves/
public class NumberOfEnclaves {
    public static void main(String[] args) {
        NumberOfEnclaves obj = new NumberOfEnclaves();
        System.out.println(obj.numEnclaves(new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}));
    }

    public int numEnclaves(int[][] mat) {
        int rows = mat.length;
        if (rows == 0) return 0;

        int cols = mat[0].length;

        boolean[][] visited = new boolean[rows][cols];

        // top row
        for (int col = 0; col < cols; col++) {
            if (mat[0][col] == 1) {
                dfs(mat, visited, 0, col);
            }
        }

        // bottom row
        for (int col = 0; col < cols; col++) {
            if (mat[rows - 1][col] == 1) {
                dfs(mat, visited, rows - 1, col);
            }
        }

        // first col
        for (int row = 0; row < rows; row++) {
            if (mat[row][0] == 1) {
                dfs(mat, visited, row, 0);
            }
        }

        // last col
        for (int row = 0; row < rows; row++) {
            if (mat[row][cols - 1] == 1) {
                dfs(mat, visited, row, cols - 1);
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && !visited[i][j]) {
                    ++count;
                }
            }
        }


        return count;
    }


    private void dfs(int[][] mat, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= mat.length) return;
        if (c < 0 || c >= mat[r].length) return;
        if (visited[r][c] || mat[r][c] == 0) return;

        visited[r][c] = true;

        dfs(mat, visited, r + 1, c);
        dfs(mat, visited, r - 1, c);
        dfs(mat, visited, r, c + 1);
        dfs(mat, visited, r, c - 1);
    }
}
