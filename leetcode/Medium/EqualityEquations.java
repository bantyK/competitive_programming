import java.util.*;

// 990 https://leetcode.com/problems/satisfiability-of-equality-equations/
public class EqualityEquations {
    public static void main(String[] args) {
        EqualityEquations obj = new EqualityEquations();

        final String[] equations = {"a==b", "e==c", "b==c", "a!=e"};


        System.out.println(obj.equationsPossible2(equations));
        System.out.println(obj.equationsPossible(equations));
    }

    /**
     * Union find. Beats 100%
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        for (String equation : equations) {
            int var1 = equation.charAt(0) - 'a';
            int var2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '=') {
                union(parent, var1, var2);
            }
        }

        for (String equation : equations) {
            int var1 = equation.charAt(0) - 'a';
            int var2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '!') {
                if (find(parent, var1) == find(parent, var2)) {
                    return false;
                }
            }
        }

        return true;
    }

    private int find(int[] parent, int variable) {
        if (parent[variable] == variable) return variable;
        return find(parent, parent[variable]);
    }

    private void union(int[] parent, int var1, int var2) {
        int p1 = find(parent, var1);
        int p2 = find(parent, var2);

        if (p1 != p2) parent[p2] = p1;
    }

    /**
     * DFS. Slow
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible2(String[] equations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (String equation : equations) {
            int var1 = equation.charAt(0) - 'a';
            int var2 = equation.charAt(3) - 'a';

            if (equation.charAt(1) == '=') {
                graph.putIfAbsent(var1, new ArrayList<>());
                graph.get(var1).add(var2);

                graph.putIfAbsent(var2, new ArrayList<>());
                graph.get(var2).add(var1);
            }
        }

        for (String equation : equations) {
            int var1 = equation.charAt(0) - 'a';
            int var2 = equation.charAt(3) - 'a';

            if (equation.charAt(1) == '!') {
                if (canReach(graph, var1, var2, new boolean[26])) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canReach(Map<Integer, List<Integer>> graph, int src, int dest, boolean[] visited) {
        if (src == dest) return true;
        visited[src] = true;
        for (int nei : graph.get(src)) {
            if (!visited[nei]) {
                if (canReach(graph, nei, dest, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

}
