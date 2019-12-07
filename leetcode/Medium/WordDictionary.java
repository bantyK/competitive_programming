// 211 https://leetcode.com/problems/add-and-search-word-data-structure-design/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordDictionary {
    TrieNode root = null;
    Map<Integer, Set<String>> countWordMap;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode(' ');
        countWordMap = new HashMap<>();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode t = root;
        countWordMap.computeIfAbsent(word.length(), k -> new HashSet<>());
        countWordMap.get(word.length()).add(word);
        TrieNode[] children = root.children;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (children[index] != null) {
                t = children[index];
            } else {
                t = new TrieNode(word.charAt(i));
                children[index] = t;
            }

            children = t.children;

            if (i == word.length() - 1) {
                t.endOfWord = true;
            }
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    private boolean search(String word) {
        if (countWordMap.containsKey(word.length())) {
            if (countWordMap.get(word.length()).contains(word)) return true;
        }
        return searchHelper(root, word);
    }

    public boolean searchHelper(TrieNode root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                boolean tempRes;
                for (int j = 0; j < 26; j++) {
                    tempRes = searchHelper(root, (char) (97 + j) + word.substring(i + 1));
                    if (tempRes) return true
                            ;
                }
                return false;
            } else {
                root = root.children[c - 'a'];
                if (root == null) return false;
            }
        }

        return root.endOfWord;
    }

    static class TrieNode {
        char c;
        TrieNode[] children;
        boolean endOfWord;

        TrieNode(char c) {
            this.c = c;
            children = new TrieNode[26];
        }
    }
}