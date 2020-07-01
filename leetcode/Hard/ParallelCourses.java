package topsort;

import java.util.*;

//136 https://leetcode.com/problems/parallel-courses/
public class ParallelCourses {
    public static void main(String[] args) {
        ParallelCourses obj = new ParallelCourses();
    }

    public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[N + 1];

        for (int[] rel : relations) {
            graph.putIfAbsent(rel[0], new ArrayList<>());
            graph.get(rel[0]).add(rel[1]);
            indegrees[rel[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        int n = 0;
        int sems = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sems++;

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (graph.containsKey(curr)) {
                    for (int neigh : graph.get(curr)) {
                        if (--indegrees[i] == 0) {
                            queue.add(neigh);
                        }
                    }
                }
            }
        }

        return sems;
    }

}
