import java.util.*;

// 444 https://leetcode.com/problems/sequence-reconstruction/
public class SequenceReconstruction {
    public static void main(String[] args) {
        SequenceReconstruction obj = new SequenceReconstruction();
        List<List<Integer>> seqs = Arrays.asList(Arrays.asList(5, 2, 6, 3), Arrays.asList(4, 1, 5, 2));
//        System.out.println(obj.sequenceReconstruction(new int[]{4, 1, 5, 2, 6, 3}, seqs));

        List<List<Integer>> seqs2 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3));
//        System.out.println(obj.sequenceReconstruction(new int[]{1,2,3}, seqs2));

        List<List<Integer>> seqs3 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3));
        System.out.println(obj.sequenceReconstruction(new int[]{1, 2, 3}, seqs3));
    }

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = buildGraph(seqs, indegree);
        Queue<Integer> queue = new LinkedList<>();

        int numVertex = indegree.size(); // the total number of vertices that are available in the
        // graph build using the given sequence list.

        if (org.length != numVertex) {
            // the number of vertex in indegree map should be equal to the number of
            // vertex in original list
            return false;
        }

        boolean startVertexFound = false;

        for (int vertex : indegree.keySet()) {
            if (indegree.get(vertex) == 0) {
                queue.offer(vertex);
                startVertexFound = true;
            }
        }

        if (!startVertexFound) {
            // there is a cycle of some sort in the sequence
            return false;
        }

        int[] actualSeq = new int[numVertex];
        int i = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1) {
                // there are two vertex which can be polled from the queue, this means
                // that we can form more than one sequence.
                return false;
            }
            int curr = queue.poll();

            actualSeq[i++] = curr;

            for (int neighbors : graph.get(curr)) {
                indegree.put(neighbors, indegree.get(neighbors) - 1);
                if (indegree.get(neighbors) == 0) {
                    queue.offer(neighbors);
                }
            }
        }

        for (int j = 0; j < indegree.size(); j++) {
            if (actualSeq[j] != org[j]) {
                // this case will arise if the original sequence and actual sequence are of same \
                // length but their values are different
                return false;
            }
        }

        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(List<List<Integer>> seqs, Map<Integer, Integer> indegree) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int num : seq) {
                indegree.put(num, 0);
                graph.put(num, new ArrayList<>());
            }
        }

        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size() - 1; i++) {
                int from = seq.get(i);
                int to = seq.get(i + 1);

                if (!graph.get(from).contains(to)) {
                    // only add the new edge if it is not already added
                    graph.get(from).add(to);
                    indegree.put(to, indegree.get(to) + 1);
                }
            }
        }

        return graph;
    }
}
