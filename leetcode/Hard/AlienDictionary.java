import java.util.*;

// 269 https://leetcode.com/problems/alien-dictionary
public class AlienDictionary {
    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();
        System.out.println(obj.alienOrder(new String[]{"wrt","wrf","er","ett","rtff"}));
    }

    public String alienOrder(String[] words) {
        boolean[][] graph = new boolean[26][26];
        int[] visited = new int[26];
        Arrays.fill(visited, -1);

        for (String word : words) {
            for (char c : word.toCharArray()) {
                visited[c - 'a'] = 0;
            }
        }
        buildGraph(words, graph);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (visited[i] == 0) {
                // not visited yet
                if (!dfs(graph, visited, builder, i)) {
                    return "";
                }
            }
        }
        return builder.reverse().toString();
    }

    private boolean dfs(boolean[][] graph, int[] visited, StringBuilder builder, int i) {
        visited[i] = 1; // visiting
        for (int j = 0; j < 26; j++) {
            if (graph[i][j]) {
                if (visited[j] == 1) {
                    return false;
                }

                if (visited[j] == 0) {
                    if (!dfs(graph, visited, builder, j)) return false;
                }
            }
        }

        visited[i] = 2; // visited
        builder.append((char)(i + 'a'));
        return true;
    }

    private void buildGraph(String[] words, boolean[][] graph) {
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    graph[c1 - 'a'][c2 - 'a'] = true;
                }
            }
        }
    }
}
