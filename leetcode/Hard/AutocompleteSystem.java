import java.util.*;

//642 https://leetcode.com/problems/design-search-autocomplete-system/
// Implementation using HashMap

class AutocompleteSystem {

    StringBuilder builder = new StringBuilder();
    private Map<String, Integer> map;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem obj = new AutocompleteSystem(new String[]{"i love you", "island", "iroman", "i love leetcode"}, new int[]{5, 3, 2, 2});
        System.out.println(obj.input('i'));
        System.out.println(obj.input(' '));
        System.out.println(obj.input('a'));
        System.out.println(obj.input('#'));
        System.out.println(obj.input('i'));

    }

    public List<String> input(char c) {
        if (c == '#') {
            // end of input
            String newLine = builder.toString();
            map.put(newLine, map.getOrDefault(newLine, 0) + 1);
            builder = new StringBuilder();
            return new ArrayList<>();
        } else {
            builder.append(c);
            return getHotSentences(map, builder.toString());
        }
    }

    private List<String> getHotSentences(Map<String, Integer> map, String prefix) {
        List<String> res = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            if (n1.hot == n2.hot) return n1.sentence.compareTo(n2.sentence);
            else return n2.hot - n1.hot;
        });

        for (String sentence : map.keySet()) {
            if (sentence.startsWith(prefix)) {
                pq.offer(new Node(sentence, map.get(sentence)));
            }
        }

        while (!pq.isEmpty() && res.size() < 3) {
            res.add(pq.poll().sentence);
        }

        return res;
    }

    static class Node {
        String sentence;
        int hot;

        public Node(String sentence, int hot) {
            this.sentence = sentence;
            this.hot = hot;
        }
    }
}