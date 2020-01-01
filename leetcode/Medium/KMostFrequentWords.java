import java.util.*;

// 692 https://leetcode.com/problems/top-k-frequent-words/
public class KMostFrequentWords {
    public static void main(String[] args) {
        KMostFrequentWords obj = new KMostFrequentWords();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 3;
        System.out.println(obj.topKFrequent(words, k));
    }

    public List<String> topKFrequent1(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words.length == 0 || k == 0) return res;
        Map<String, Integer> map = new HashMap<>();

        for (String s : words) {
            map.putIfAbsent(s, 0);
            map.put(s, map.get(s) + 1);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> (a.getValue().equals(b.getValue())) ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }

        return res;
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words.length == 0 || k == 0) return res;
        Arrays.sort(words);
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String s : words) {
            map.putIfAbsent(s, 0);
            map.put(s, map.get(s) + 1);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        int i = k;
        for (Map.Entry<String, Integer> entry : entryList) {
            res.add(entry.getKey());
            i--;
            if (i == 0) break;
        }

        return res;
    }
}