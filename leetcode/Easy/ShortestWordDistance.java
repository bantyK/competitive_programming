import java.util.*;

//243 https://leetcode.com/problems/shortest-word-distance/submissions/
public class ShortestWordDistance {
    public static void main(String[] args) {
        ShortestWordDistance obj = new ShortestWordDistance();
        String[] words = new String[]{"practice", "makes", "perfect", "something", "coding", "something", "something", "something", "practice", "something", "coding", "makes"};
        System.out.println(obj.shortestDistance(words, "coding", "practice"));

    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int minLength = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            } else if (words[i].equals(word2)) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                minLength = Math.min(minLength, Math.abs(idx1 - idx2));
            }

            if (minLength == 1) return 1;
        }
        return minLength;
    }

    public int shortestDistanceUsingTreeMap(String[] words, String word1, String word2) {
        Map<String, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new TreeSet<>());
            map.get(words[i]).add(i);
        }

        int minDistance = words.length;
        TreeSet<Integer> word1Indices = map.get(word1);

        for (int index : word1Indices) {
            Integer ceiling = map.get(word2).ceiling(index);
            Integer floor = map.get(word2).floor(index);

            if (ceiling != null) {
                minDistance = Math.min(minDistance, Math.abs(index - ceiling));
            }
            if (floor != null) {
                minDistance = Math.min(minDistance, Math.abs(index - floor));
            }
        }

        return minDistance;
    }
}