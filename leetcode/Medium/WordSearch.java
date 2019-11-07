package solutions.medium;

// https://leetcode.com/problems/word-search/
public class WordSearch {

    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] chars = new char[][]{
                {'C', 'A', 'A'}
                , {'A', 'A', 'A'}
                , {'B', 'C', 'D'}};

        // this is failing. Need to watch video for this
        System.out.println(obj.exist(chars, "AAB"));

    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == word.charAt(0) && restOfTheWordFound(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean restOfTheWordFound(char[][] board, int row, int col, int numCharsFound, String word) {
        if (numCharsFound == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length || board[row][col] != word.charAt(numCharsFound)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = ' ';

        boolean found = restOfTheWordFound(board, row + 1, col, numCharsFound + 1, word)
                || restOfTheWordFound(board, row - 1, col, numCharsFound + 1, word)
                || restOfTheWordFound(board, row, col + 1, numCharsFound + 1, word)
                || restOfTheWordFound(board, row, col - 1, numCharsFound + 1, word);

        board[row][col] = temp;

        return found;
    }


}
