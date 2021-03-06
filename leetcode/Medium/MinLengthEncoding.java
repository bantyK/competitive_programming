import java.util.*;

//820 https://leetcode.com/problems/short-encoding-of-words/
public class MinLengthEncoding {

    TrieNode root;

    public static void main(String[] args) {
        MinLengthEncoding obj = new MinLengthEncoding();
        System.out.println(obj.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(obj.minimumLengthEncoding(new String[]{"time", "me", "ime"}));
        System.out.println(obj.minimumLengthEncoding(new String[]{"abcdef", "cd"}));
        System.out.println(obj.minimumLengthEncoding(new String[]{"t"}));
    }

    public int minimumLengthEncoding(String[] words) {
        root = new TrieNode();
        int len = 0;
        for (String word : words) {
            if (addWord(word)) {
                len += word.length() + 1;
            }
        }
        return len;
    }

    private boolean addWord(String word) {
        boolean createdNewTrieNode = false;
        TrieNode node = root;

        // adding the word from the end
        // Originally we have to compare suffixes but if we reverse the words, then we have to
        // compare prefixes. And we can use Tries.
        for (int i = word.length() - 1; i >= 0; i--) {
            int index = word.charAt(i) - 'a';
            // If this word is already a prefix of some another word, then we don't have to create any new node for
            // this word. And hence we dont have to add anything to the encoding
            if (node.children[index] == null) {
                createdNewTrieNode = true;
                TrieNode newNode = new TrieNode();
                node.children[index] = newNode;
            }
            node = node.children[index];
        }

        return createdNewTrieNode;
    }

    // Brute Force
    public int minimumLengthEncoding2(String[] words) {
        int len = 0;
        Arrays.sort(words, (w1, w2) -> w2.length() - w1.length());

        List<String> usedWords = new ArrayList<>();

        for (String word : words) {
            boolean add = true;
            for (String used : usedWords) {
                if (used.endsWith(word)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                len += word.length() + 1;
            }
            usedWords.add(word);
        }
        return len;
    }

    class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}