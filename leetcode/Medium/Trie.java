// 208 https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode[] children = root.children;
        TrieNode t = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(children[index] == null) {
                t = new TrieNode(c);
                children[index] = t;
            } else {
                t = children[index];
            }
            children = t.children;

            if(i == word.length() - 1) {
                t.endOfWord = true;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode t = root;

        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(t.children[index]  != null) {
                t = t.children[index];
            } else {
                return false;
            }
        }

        return t.endOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode t = root;

        for(int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if(t.children[index]  != null) {
                t = t.children[index];
            } else {
                return false;
            }
        }

        return true;
    }

    static class TrieNode {
        char c;
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord;

        TrieNode(char c) { this.c = c; }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */