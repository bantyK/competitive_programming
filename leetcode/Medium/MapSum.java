import java.util.*;
// 677 https://leetcode.com/problems/map-sum-pairs/
public class MapSum {
    Trie trie;

    public MapSum() {
        trie = new Trie();
    }

    public void insert(String key, int val) {
        trie.insert(key, val);
    }

    public int sum(String prefix) {
        return trie.search(prefix);
    }

    private class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode('\0');
        }

        public void insert(String key, int value) {
            insertHelper(root, key.toCharArray(), 0, value);
        }

        private void insertHelper(TrieNode root, char[] chars, int index, int value) {
            if (index >= chars.length) return;

            int charIndex = chars[index] - 'a';
            TrieNode node;

            if (root.children[charIndex] == null) {
                node = new TrieNode(chars[index]);
                root.children[charIndex] = node;
            } else {
                node = root.children[charIndex];
            }

            if (index == chars.length - 1) {
                node.isWord = true;
                node.value = value;
            }

            insertHelper(node, chars, index + 1, value);
        }

        public int search(String prefix) {
            TrieNode searchStartNode = prefixExist(root, prefix, 0);
            int sum = 0;
            if(searchStartNode != null) {
                sum = findSum(searchStartNode);
            }

            return sum;
        }

        private int findSum(TrieNode node) {
            int sum = node.value;
            for(TrieNode child : node.children) {
                if(child != null) {
                    sum += findSum(child);
                }
            }
            return sum;
        }

        private TrieNode prefixExist(TrieNode root, String prefix, int index) {
            if(index == prefix.length()) return root;

            int childIndex = prefix.charAt(index) - 'a';
            if(root.children[childIndex] == null) return null;
            else return prefixExist(root.children[childIndex], prefix, index + 1);
        }


    }

    private class TrieNode {
        int value;
        char ch;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char c) {
            value = 0;
            ch = c;
            children = new TrieNode[26];
            isWord = false;
        }
    }
}
