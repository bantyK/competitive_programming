// 576 https://leetcode.com/problems/out-of-boundary-paths/
public class OutOfBoundaryPaths {
    private int MOD = 1000000007;

    public static void main(String[] args) {
        OutOfBoundaryPaths obj = new OutOfBoundaryPaths();
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        Long[][][] dp = new Long[m][n][N + 1];
        return (int) (dfs(i, j, N, m, n, dp) % MOD);
    }

    private long dfs(int x, int y, int remainingMoves, int rows, int cols, Long[][][] dp) {
        if (x == rows || x < 0 || y < 0 || y == cols) {
            return 1;
        }

        if (remainingMoves == 0) return 0;

        if (dp[x][y][remainingMoves] != null) return dp[x][y][remainingMoves];

        long ans = dfs(x + 1, y, remainingMoves - 1, rows, cols, dp)
                + dfs(x - 1, y, remainingMoves - 1, rows, cols, dp)
                + dfs(x, y + 1, remainingMoves - 1, rows, cols, dp)
                + dfs(x, y - 1, remainingMoves - 1, rows, cols, dp);

        ans = ans % MOD;

        return dp[x][y][remainingMoves] = ans;
    }

}