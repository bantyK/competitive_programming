//174 https://leetcode.com/problems/dungeon-game/
public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame obj = new DungeonGame();

        int[][] dungeon = new int[][]{{0, -3}};
        System.out.println(obj.calculateMinimumHP(dungeon));
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        if (rows == 0) return 0;

        int cols = dungeon[0].length;

        int[][] dp = new int[rows][cols];
        dp[rows - 1][cols - 1] = Math.max(1, 1 - dungeon[rows - 1][cols - 1]);

        // last column
        int lastCol = cols - 1;
        for (int row = rows - 2; row >= 0; --row) {
            dp[row][lastCol] = Math.max(1, dp[row + 1][lastCol] - dungeon[row][lastCol]);
        }

        // last row
        int lastRow = rows - 1;
        for (int col = cols - 2; col >= 0; --col) {
            dp[lastRow][col] = Math.max(1, dp[lastRow][col + 1] - dungeon[lastRow][col]);
        }

        int val;
        for (int col = cols - 2; col >= 0; --col) {
            for (int row = rows - 2; row >= 0; --row) {
                val = Math.min(dp[row + 1][col], dp[row][col + 1]) - dungeon[row][col];
                dp[row][col] = Math.max(1, val);
            }
        }

//        Util.print2dArray(dp);

        return dp[0][0];
    }
}
