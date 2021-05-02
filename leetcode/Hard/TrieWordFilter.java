import java.util.*;

// 745 https://leetcode.com/problems/prefix-and-suffix-search/
public class TrieWordFilter {
    public static void main(String[] args) {

//        ["WordFilter","f","f","f","f","f","f","f","f","f","f"]

        //[],[],[],[],[],[],[],[]]
        WordFilter wf = new WordFilter(new String[]{"cabaabaaaa", "ccbcababac", "bacaabccba", "bcbbcbacaa", "abcaccbcaa", "accabaccaa", "cabcbbbcca", "ababccabcb", "caccbbcbab", "bccbacbcba"});
        System.out.println(wf.f("bccbacbcba", "a"));
        System.out.println(wf.f("ab", "abcaccbcaa"));
        System.out.println(wf.f("a", "aa"));
        System.out.println(wf.f("cabaaba", "abaaaa"));
        System.out.println(wf.f("cacc", "accbbcbab"));
        System.out.println(wf.f("ccbcab", "bac"));
        System.out.println(wf.f("bac", "cba"));
        System.out.println(wf.f("ac", "accabaccaa"));
        System.out.println(wf.f("bcbb", "aa"));
        System.out.println(wf.f("ccbca", "cbcababac"));
    }

    static class WordFilter {

        Map<String, Integer> wordIndexMap;
        TrieNode root;

        public WordFilter(String[] words) {
            wordIndexMap = new HashMap<>();
            root = new TrieNode('\0');
            for (int i = words.length - 1; i >= 0; --i) {
                if (!wordIndexMap.containsKey(words[i])) {
                    wordIndexMap.put(words[i], i);
                    addWord(words[i]);
                }
            }

        }

        public int f(String prefix, String suffix) {
            List<String> prefixWords = wordsWithPrefix(prefix);
            for (String word : prefixWords) {
                if (word.endsWith(suffix)) {
                    return wordIndexMap.get(word);
                }
            }
            return -1;
        }

        private List<String> wordsWithPrefix(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) {
                    return Collections.emptyList();
                } else {
                    curr = curr.children[idx];
                }
            }
            return curr.words;
        }

        private void addWord(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                TrieNode trieNode = curr.children[idx];
                if (trieNode == null) {
                    curr.children[idx] = new TrieNode(c);
                }

                curr.children[idx].words.add(word);
                curr = curr.children[idx];
            }
        }
    }

    private static class TrieNode {
        char c;
        List<String> words;
        TrieNode[] children;

        public TrieNode(char c) {
            this.c = c;
            words = new ArrayList<>();
            children = new TrieNode[26];
        }
    }
}