package solutions.easy;

public class CellWithOddValues {
    
    public int oddCells(int n, int m, int[][] indices) {
        int[][] res = new int[n][m];

        if (indices.length == 0) return 0;

        for (int[] index : indices) {

            int row = index[0];
            int col = index[1];

            for (int i = 0; i < m; i++) {
                res[row][i] += 1;
            }

            for (int i = 0; i < n; i++) {
                res[i][col] += 1;
            }
        }

        int oddValue = 0;

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (res[i][j] % 2 == 1) {
                    oddValue += 1;
                }
            }
        }

        return oddValue;
    }
}
