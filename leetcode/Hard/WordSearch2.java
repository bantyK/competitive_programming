import java.util.*;

// 212 https://leetcode.com/problems/word-search-ii/
public class WordSearch {
    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        final List<String> words = obj.findWords(board, new String[]{"oath"});

        System.out.println("Found words");
        System.out.println(new HashSet<String>(words));

    }


    /**
     * Backtracking solution
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        for (String word : words) {
            boolean wordFound = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0) && checkWord(board, word, i, j, 0)) {
                        res.add(word);
//                        System.out.println("Word found : " + word);
                        wordFound = true;
                        break;
                    }
                }

                if (wordFound) break;
            }
        }

        return res;
    }

    private boolean checkWord(char[][] board, String word, int i, int j, int pos) {
        if (pos == word.length()) return true;
        if (i < 0 || i >= board.length) return false;
        if (j < 0 || j >= board[i].length) return false;
        if (word.charAt(pos) != board[i][j]) return false;

        char temp = board[i][j];
        board[i][j] = ' ';
        boolean found = checkWord(board, word, i + 1, j, pos + 1)
                || checkWord(board, word, i - 1, j, pos + 1)
                || checkWord(board, word, i, j + 1, pos + 1)
                || checkWord(board, word, i, j - 1, pos + 1);

        board[i][j] = temp;
        return found;

    }
}

