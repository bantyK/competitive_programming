import java.util.*;

// 212 https://leetcode.com/problems/word-search-ii/
public class WordSearch2 {
    public static void main(String[] args) {
        WordSearch2 obj = new WordSearch2();
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

    //Trie Solution
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrieNode(words);
        List<String> res = new ArrayList<>();

        int rows = board.length;
        if (rows == 0) return res;
        int cols = board[0].length;


        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; col++) {
                dfs(board, root, row, col, res);
            }
        }

        return res;
    }


    private void dfs(char[][] board, TrieNode root, int row, int col, List<String> res) {
        if (row < 0 || row >= board.length) return;
        if (col < 0 || col >= board[0].length) return;
        if(board[row][col] == '#') return;

        char c = board[row][col];
        int index = c - 'a';
        if (root.children[index] == null) return;
        root = root.children[index];

        if (root.word != null) {
            res.add(root.word);
            root.word = null;
        }

        board[row][col] = '#';

        dfs(board, root, row + 1, col, res);
        dfs(board, root, row - 1, col, res);
        dfs(board, root, row, col + 1, res);
        dfs(board, root, row, col - 1, res);

        board[row][col] = c;
    }

    private TrieNode buildTrieNode(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Backtracking solution
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords2(char[][] board, String[] words) {
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

