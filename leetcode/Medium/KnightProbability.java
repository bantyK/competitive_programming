//688 https://leetcode.com/problems/knight-probability-in-chessboard/
public class KnightProbability {

    private static int[][] moves = new int[][]{{-1, 2}, {-2, 1}, {1, 2}, {2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};

    public static void main(String[] args) {
        KnightProbability obj = new KnightProbability();
        System.out.println(obj.knightProbability(3, 1, 0, 0));
    }

    public double knightProbability(int n, int k, int row, int column) {
        Double[][][] dp = new Double[n][n][k + 1];
        return dfs(n, k, row, column, 1.0, dp);
    }

    private double dfs(int n, int k, int row, int col, double prob, Double[][][] dp) {
        if (k == 0) return prob;
        if (dp[row][col][k] != null) return dp[row][col][k];
        double ans = 0;
        for (int[] move : moves) {
            int dx = row + move[0];
            int dy = col + move[1];
            if (insideChess(n, dx, dy)) {
                ans += dfs(n, k - 1, dx, dy, prob * 0.125, dp);
            }
        }
        return dp[row][col][k] = ans;
    }

    private boolean insideChess(int n, int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }


}