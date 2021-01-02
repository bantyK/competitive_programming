import java.util.*;

// 269 https://leetcode.com/problems/alien-dictionary/
public class AlienDictionary {
    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();
        // System.out.println(obj.alienOrder(new String[] { "wrt", "wrf", "er", "ett",
        // "rftt" }));
        System.out.println(obj.alienOrder(new String[] { "ac", "ab", "zc", "zb" }));
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> indegreeMap = new HashMap<>();

        // init the data structures
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegreeMap.put(c, 0);
                adjList.putIfAbsent(c, new ArrayList<>());
            }
        }

        // create a graph to represent the relationships between characters
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            // if word2 is the prefix of word1, then we cannot derive any useful information
            // about letter ordering, and this is an invalid input
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }

            // Find the first non-matching character. Because thats the point which is
            // useful for finding the
            // character ordering, characters after the unmatched character have no role in
            // finding the
            // relative ordering of characters
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    indegreeMap.put(word2.charAt(j), indegreeMap.get(word2.charAt(j)) + 1);
                    break; // important, no need to move ahead in the words
                }
            }
        }

        // graph is ready, we will start the BFS from here
        StringBuilder builder = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for (char c : indegreeMap.keySet()) {
            if (indegreeMap.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            builder.append(c);

            for (char next : adjList.get(c)) {
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        if (builder.length() < indegreeMap.size()) {
            // we are not able to determine the ordering of all characters in the input.
            return "";
        }

        return builder.toString();
    }
}