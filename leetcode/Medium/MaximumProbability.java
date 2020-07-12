package bfs;

import java.util.*;

//1541 https://leetcode.com/problems/path-with-maximum-probability/
public class MaximumProbability {
    public static void main(String[] args) {
        MaximumProbability obj = new MaximumProbability();
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());

            graph.get(edge[0]).add(new Edge(edge[1], succProb[i]));
            graph.get(edge[1]).add(new Edge(edge[0], succProb[i]));
        }

        // best probabilities for each node
        double[] bestProbabilities = new double[n];

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(start, 1.0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int node = curr.node;
            double prob = curr.probability;


            if (graph.containsKey(node)) {
                List<Edge> neighbors = graph.get(node); // negihbour - prob for that neighbour pair

                for (Edge neighbor : neighbors) {
                    int next = neighbor.to;

                    double newProb = prob * neighbor.prob;
                    if (bestProbabilities[next] >= newProb) continue;
                    bestProbabilities[next] = newProb;
                    queue.offer(new Pair(next, newProb));
                }
            }
        }

        return bestProbabilities[end];
    }

    static class Edge {
        int to;
        double prob;

        public Edge(int to, double prob) {
            this.to = to;
            this.prob = prob;
        }
    }

    static class Pair {
        int node;
        double probability;

        public Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}
