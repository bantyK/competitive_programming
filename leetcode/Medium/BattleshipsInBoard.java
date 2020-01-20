// 419 https://leetcode.com/problems/battleships-in-a-board/
public class BattleshipsInBoard {
    public static void main(String[] args) {
        BattleshipsInBoard obj = new BattleshipsInBoard();
        char[][] board = new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };

        System.out.println(obj.countBattleships(board));
    }

    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    count += sink(board, i, j);
                }
            }
        }

        return count;
    }

    private int sink(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length) return 0;
        if (j < 0 || j >= board[i].length) return 0;
        if (board[i][j] == '.') return 0;

        board[i][j] = '.';
        sink(board, i + 1, j);
        sink(board, i - 1, j);
        sink(board, i, j + 1);
        sink(board, i, j - 1);

        return 1;
    }

    /**
     * Follow up questions
     *
     * @param board
     * @return
     */
    public int countBattleships2(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                } else if (i > 0 && board[i - 1][j] == 'X') continue;
                else if (j > 0 && board[i][j - 1] == 'X') continue;
                else count++;
            }
        }

        return count;
    }
}
