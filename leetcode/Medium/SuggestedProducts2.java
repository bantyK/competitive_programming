package solutions.medium;

import java.util.*;
public class SuggestedProducts {
    public static void main(String[] args) {
        List<List<String>> res = suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
        for(List<String> r : res) {
            System.out.println(r);
        }
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for(String product : products) {
            TrieNode curr = root;
            for(char c : product.toCharArray()) {
                if(curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.word = word;
            curr.count++;
        }

        List<List<String>> result = new ArrayList<>();
        TrieNode curr = root;

        for(char c : searchWord.toCharArray()) {
            if(curr != null) {
                curr = curr.children[c - 'a'];
                ArrayList<String> res = new ArrayList<>();
                find3WordLexicographically(curr, res);
                result.add(res);
            } else {
                result.add(new ArrayList<>());
            }
        }
    }

    private static void find3WordLexicographically(TrieNode root, ArrayList<String> result) {
        if(root == null || root.size() == 3) return;
        int size = Math.min(root.count, 3 - result.size());
        for(int i = 0; i < size; i++) {
            result.add(root.word);
        }

        for(TrieNode child : root.children) {
            find3WordLexicographically(child, result);
        }
    }

    private static class TrieNode {
        int SIZE = 26;
        TrieNode children[] = new TrieNode[SIZE];
        String word = null;
        int count = 0;
    }
}