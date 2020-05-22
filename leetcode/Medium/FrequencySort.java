import java.util.*;

//451 https://leetcode.com/problems/sort-characters-by-frequency/
public class FrequencySort {
    public static void main(String[] args) {
        FrequencySort obj = new FrequencySort();

        System.out.println(obj.frequencySort("tree"));
        System.out.println(obj.frequencySort("cccaaa"));
        System.out.println(obj.frequencySort("Aabb"));
    }

    public String frequencySort(String s) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> b.count - a.count);
        char[] chars = s.toCharArray();
        Map<Character, Integer> countMap = new HashMap<>();

        for(char c : chars) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for(char c : countMap.keySet()) {
            maxHeap.add(new Pair(c, countMap.get(c)));
        }

        StringBuilder builder = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();
            builder.append(String.valueOf(pair.c).repeat(Math.max(0, pair.count)));
        }

        return builder.toString();
    }

    private static class Pair {
        final char c;
        final int count;


        private Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

}
