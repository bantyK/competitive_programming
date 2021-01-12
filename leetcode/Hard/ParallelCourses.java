import input.TwoDArrayReader;

import java.util.*;

//1136 https://leetcode.com/problems/parallel-courses/
public class ParallelCourses {
    public static void main(String[] args) {
        ParallelCourses obj = new ParallelCourses();
        int[][] relations = new TwoDArrayReader().get2DArray();
        System.out.println(obj.minimumSemesters(27, relations));
    }

    Map<Integer, List<Integer>> graph;
    Integer[] dp;

    public int minimumSemesters(int N, int[][] relations) {
        graph = new HashMap<>();
        dp = new Integer[N];

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] relation : relations) {
            int pre = relation[0] - 1; // input given in 1-based
            int current = relation[1] - 1;
            // pre is the pre-requisite for current node
            graph.get(current).add(pre); // create the graph.
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            // this set contains all the nodes which are being processed currently
            // If we are processing a node and that node was already present in this set
            // then, it means there is a cycle at this node. This helps us indetify the
            // cycle in the graph.
            Set<Integer> currentStack = new HashSet<>();
            currentStack.add(i);
            ans = Math.max(ans, dfs(i, currentStack));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int currentNode, Set<Integer> currentStack) {
        // if we have already visited this node, then no need to compute it further.
        // simply return
        if (dp[currentNode] != null) {
            return dp[currentNode];
        }

        // leaf node, the course which has no prerequisite
        if (graph.get(currentNode).size() == 0) {
            dp[currentNode] = 1;
            return 1; // this course can be studied in the first semester itself. It has no prerequisite
        }

        int maxSemester = 0;
        for (int prerequisite : graph.get(currentNode)) {
            if (currentStack.contains(prerequisite)) {
                dp[prerequisite] = Integer.MAX_VALUE;
                return Integer.MAX_VALUE;
            }
            currentStack.add(prerequisite);
            maxSemester = Math.max(maxSemester, dfs(prerequisite, currentStack));
            currentStack.remove(prerequisite);
        }


        if (maxSemester != Integer.MAX_VALUE) {
            maxSemester++; // +1 because we need one semester to take the current nodes
        }

        dp[currentNode] = maxSemester;
        return maxSemester;
    }
}
