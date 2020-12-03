import java.util.*;

//244 https://leetcode.com/problems/shortest-word-distance-ii/
public class ShortestWordDistance2 {

    public static void main(String[] args) {

    }

    class WordDistance {
        Map<String, List<Integer>> map;

        public WordDistance(String[] words) {
            map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                map.putIfAbsent(words[i], new ArrayList<>());
                map.get(words[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> idx1 = map.get(word1);
            List<Integer> idx2 = map.get(word2);

            int minDistance = Integer.MAX_VALUE;

            int i = 0, j = 0;

            while (i < idx1.size() && j < idx2.size()) {
                int index1 = idx1.get(i);
                int index2 = idx2.get(j);

                if (index1 > index2) {
                    minDistance = Math.min(minDistance, index1 - index2);
                    i++;
                } else {
                    minDistance = Math.min(minDistance, index2 - index1);
                    j++;
                }
            }

            return minDistance;
        }
    }


}