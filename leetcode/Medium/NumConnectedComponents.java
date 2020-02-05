import java.util.*;

//323 https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class NumConnectedComponents {
    public static void main(String[] args) {
        NumConnectedComponents obj = new NumConnectedComponents();
        System.out.println(obj.countComponents(5, new int[][]{{0,1},{1,4},{1,2},{1,3},{2,3}}));
    }

    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            union(parent, edge[0], edge[1]);
        }

        Set<Integer> seen = new HashSet<>();
        int components = 0;

        for (int value : parent) {
            if (!seen.contains(value)) {
                seen.add(value);
                components += 1;
            }
        }

        return components;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        else return parent[i] = find(parent, parent[i]);
    }

    private void union(int[] parent, int i, int j) {
        int parentI = find(parent, i);
        int parentJ = find(parent, j);
        if (parentI != parentJ) {
            parent[parentJ] = parentI;
        }
    }
}
