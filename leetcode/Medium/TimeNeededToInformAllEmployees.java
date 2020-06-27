package dfs;

import java.util.*;

//1376 https://leetcode.com/problems/time-needed-to-inform-all-employees/
public class TimeNeededToInformAllEmployees {

    public static void main(String[] args) {
        TimeNeededToInformAllEmployees obj = new TimeNeededToInformAllEmployees();
        System.out.println(obj.numOfMinutes(16,
                0,
                new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7},
                new int[]{1, 1, 1, 1, 1, 1, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0}));

        System.out.println(obj.numOfMinutes(3, 0, new int[]{-1, 0, 0}, new int[]{1, 0, 0}));
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); // manager - subordinate graph

        int headIndex = -1;
        for (int i = 0; i < n; i++) {
            if (manager[i] >= 0) {
                int mgr = manager[i]; // mgr is the manager of i

                graph.putIfAbsent(mgr, new ArrayList<>());
                graph.get(mgr).add(i);
            }
        }

        int val = dfs(n, graph, headID, informTime, 0);
        return val;
    }

    private int dfs(int n, Map<Integer, List<Integer>> graph, int employee, int[] informTime, int time) {
        List<Integer> sub = graph.get(employee);
        if (sub == null) {
            return time;
        }

        int t = Integer.MIN_VALUE;
        for (Integer integer : sub) {
            t = Math.max(t, dfs(n, graph, integer, informTime, time + informTime[employee]));
        }

        return t;
    }
}
