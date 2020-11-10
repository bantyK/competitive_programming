import java.util.*;

//642 https://leetcode.com/problems/design-search-autocomplete-system/
// Implementation using Trie
public class AutoCompleteTrie {
    TrieNode root;
    StringBuilder builder;

    public static void main(String[] args) {

        //["i"],[" "],["a"],["#"]]
        AutoCompleteTrie obj = new AutoCompleteTrie(
                new String[]{"i love you","island","iroman","i love leetcode"},
                new int[]{5,3,2,2}
        );

        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
    }

    public AutoCompleteTrie(String[] sentences, int[] times) {
        builder = new StringBuilder();
        root = new TrieNode();

        for(int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    private void add(String sentence, int times) {
        TrieNode curr = root;
        for(char ch : sentence.toCharArray()) {
            TrieNode next = curr.children.get(ch);
            if(next == null) {
                next = new TrieNode();
                curr.children.put(ch, next);
            }
            curr = next;
            curr.counts.put(sentence, curr.counts.getOrDefault(sentence, 0) + times);
        }
    }



    public List<String> input(char c) {
        if(c == '#') {
            add(builder.toString(), 1);
            builder = new StringBuilder();
            return new ArrayList<>();
        }

        builder.append(c);
        String prefix = builder.toString();
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()) {
            TrieNode next = curr.children.get(ch);
            if(next == null) {
                return new ArrayList<>();
            }
            curr = next;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.hot == p2.hot) return p1.sentence.compareTo(p2.sentence);
            else return p2.hot - p1.hot;
        });

        for (String s : curr.counts.keySet()) {
            pq.offer(new Pair(s, curr.counts.get(s)));
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty() && res.size() < 3) {
            res.add(pq.poll().sentence);
        }

        return res;
    }


    private static class TrieNode {
        Map<Character, TrieNode> children; // need a map because sentences can contain space
        Map<String, Integer> counts;

        public TrieNode() {
            children = new HashMap<>();
            counts = new HashMap<>();
        }
    }

    private static class Pair {
        String sentence;
        int hot;

        public Pair(String s, int h) {
            sentence = s;
            hot = h;
        }
    }
}
