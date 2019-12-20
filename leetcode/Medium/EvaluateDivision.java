import java.util.*;
//399 https://leetcode.com/problems/evaluate-division
public class EvaluateDivision {
    public static void main(String[] args) {
        EvaluateDivision obj = new EvaluateDivision();

        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "f"), Arrays.asList("b", "c"), Arrays.asList("c", "d"));
        double[] values = new double[]{2.0, 3.0, 4.0, 5.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "f"), Arrays.asList("a", "c"), Arrays.asList("a", "d"), Arrays.asList("b", "a"));

        double[] solutions = obj.calcEquation(equations, values, queries);

        System.out.println(Arrays.toString(solutions));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> graph = new HashMap<>();
        double[] results = new double[queries.size()];
        Set<String> variables = new HashSet<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            if (!graph.containsKey(equation.get(0))) {
                graph.put(equation.get(0), new ArrayList<>());
            }

            variables.add(equation.get(0));
            variables.add(equation.get(1));
            visited.put(equation.get(0), false);
            visited.put(equation.get(1), false);
            graph.get(equation.get(0)).add(new Edge(equation.get(0), equation.get(1), values[i]));

            if (!graph.containsKey(equation.get(1))) {
                graph.put(equation.get(1), new ArrayList<>());
            }
            graph.get(equation.get(1)).add(new Edge(equation.get(1), equation.get(0), 1 / values[i]));
        }

        for (int i = 0; i < queries.size(); i++) {
            Map<String, Boolean> visitedMap = new HashMap<>(visited);

            results[i] = dfs(graph, queries.get(i).get(0), queries.get(i).get(1), visitedMap, 1);
        }

        return results;
    }

    private double dfs(Map<String, List<Edge>> graph, String start, String end, Map<String, Boolean> visited, double result) {
        visited.put(start, true);
        List<Edge> edges = graph.get(start);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.to.equals(end)) {
                    return edge.weight * result;
                } else {
                    if (!visited.get(edge.to)) {
                        double temp = dfs(graph, edge.to, end, visited, result * edge.weight);
                        if (temp != -1) {
                            return temp;
                        }
                    }

                }
            }
        }
        return -1;
    }

    static class Edge {
        final String from;
        final String to;
        final double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
