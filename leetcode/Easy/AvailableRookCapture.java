package solutions;

//https://leetcode.com/problems/available-captures-for-rook/
public class AvailableRookCapture {

    public static void main(String[] args) {
        char board[][] = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};


        System.out.println(new AvailableRookCapture().numRookCaptures(board));
    }

    public int numRookCaptures(char[][] board) {
        int[] coordinates = getRookCoordinates(board);
        int X = coordinates[0];
        int Y = coordinates[1];
        int count = 0;

        // check horizontal, left of rook
        for (int i = Y - 1; i >= 0; i--) {
            if (isWhitePiece(board[X][i]))
                break;
            else if (board[X][i] == 'p') {
                count++;
                break;
            }
        }

        // check horizontal, right of rook
        for (int i = Y + 1; i < 8; i++) {
            if (isWhitePiece(board[X][i]))
                break;
            else if (board[X][i] == 'p') {
                count++;
                break;
            }
        }

        // check vertical, top of rook
        for (int j = X - 1; j >= 0; j--) {
            if (isWhitePiece(board[j][Y]))
                break;
            else if (board[j][Y] == 'p') {
                count++;
                break;
            }
        }

        // check vertical, bottom of rook
        for (int j = X + 1; j < 8; j++) {
            if (isWhitePiece(board[j][Y]))
                break;
            else if (board[j][Y] == 'p') {
                count++;
                break;
            }
        }

        return count;

    }

    private boolean isWhitePiece(char c) {
        return (int) c >= 65 && (int) c <= 90;
    }

    private int[] getRookCoordinates(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
