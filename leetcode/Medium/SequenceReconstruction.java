import java.util.*;

//444 https://leetcode.com/problems/sequence-reconstruction/
public class SequenceReconstruction {
    public static void main(String[] args) {
        SequenceReconstruction obj = new SequenceReconstruction();
        int[] org = new int[]{0, 1, 2, 3};
        int[][] seq = new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        System.out.println(obj.sequenceReconstruction(org, seq));

    }

    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        List<Integer> order = new ArrayList<>();
        if (org.length <= 0) return true;

        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        buildGraph(seqs, graph, indegree);

        if (indegree.size() != org.length) return false;

        Queue<Integer> queue = new LinkedList<>();
        for (int vertex : indegree.keySet()) {
            if (indegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1) return false;

            int curr = queue.poll();
            order.add(curr);
            if (graph.containsKey(curr)) {
                for (int nei : graph.get(curr)) {
                    indegree.put(nei, indegree.get(nei) - 1);
                    if (indegree.get(nei) == 0) {
                        queue.offer(nei);
                    }
                }
            }
        }

        if (order.size() != org.length) return false;
        for (int i = 0; i < org.length; i++) {
            if (org[i] != order.get(i)) return false;
        }
        return true;
    }

    private void buildGraph(int[][] seqs, Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegree) {
        for (int[] seq : seqs) {
            for (int num : seq) {
                graph.put(num, new ArrayList<>());
                indegree.put(num, 0);
            }
        }

        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length - 1; i++) {
                int from = seq[i];
                int to = seq[i + 1];
                graph.get(from).add(to);
                indegree.put(to, indegree.get(to) + 1);
            }
        }
    }
}
