// 1275 https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe obj = new TicTacToe();
        int[][] moves = new int[][]{
                {0, 0},
                {1, 1},
                {0, 1},
                {0, 2},
                {1, 0},
                {2, 0}
        };
        System.out.println(obj.tictactoe(moves));
    }

    public String tictactoe(int[][] moves) {

        char[][] grid = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }

        char player = 'A';

        for (int i = 0; i < moves.length; i++) {
            player = i % 2 == 0 ? 'A' : 'B';

            int[] move = moves[i];

            grid[move[0]][move[1]] = player;

            if (hasWon(grid, move, player)) {
                return player + "";
            }
        }

        return moves.length == 9 ? "Draw" : "Pending";

    }

    private boolean hasWon(char[][] grid, int[] move, char player) {
        int x = move[0];
        int y = move[1];
        boolean won = true;
        int i;

        for (i = 0; i < 3; i++) {
            if (grid[x][i] != player) {
                won = false;
                break;
            }
        }

        if(i == 3) return true;
        won = true;

        for (i = 0; i < 3; i++) {
            if (grid[i][y] != player) {
                won = false;
                break;
            }
        }

        if(i == 3 && won) return true;
        won = true;

        for (i = 0; i < 3; i++) {
            if (grid[i][i] != player) {
                won = false;
                break;
            }

        }

        if(i == 3 && won) return true;
        won = true;

        for (i = 0; i < 3; i++) {
            if (grid[i][3 - i - 1] != player) {
                won = false;
                break;
            }
        }

        if(i == 3 && won) return true;

        return won;
    }
}
