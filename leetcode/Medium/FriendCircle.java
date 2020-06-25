import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 547 https://leetcode.com/problems/friend-circles/
public class FriendCircle {
    public static void main(String[] args) throws IOException {
        FriendCircle obj = new FriendCircle();
        int[][] grid = new TwoDArrayReader().get2DArray();

        int set = obj.findCircleNum(grid);
        System.out.println(set);
    }

    // DFS
    public int findCircleNumDFS(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        int group = 0;
        
        for(int i = 0; i < n; ++i) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(M, i, visited);
                group++;
            }
        }
        
        return group;
    }
    
    private void dfs(int[][] mat, int row, boolean[] visited) {
        for(int col = 0; col < mat[row].length; ++col) {
            if(mat[row][col] == 1 && !visited[col]) {
                visited[col] = true;
                dfs(mat, col, visited);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Union find method
    public int findCircleNum(int[][] M) {
        DisjointSet set = new DisjointSet(M.length);

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (i != j && M[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }

        return set.numSets;
    }

    static class DisjointSet {
        int numSets;
        int numItems;
        int[] parents;

        public DisjointSet(int n) {
            numItems = n;
            numSets = n;
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] == x) {
                return x;
            }

            return parents[x] = find(parents[x]);
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY) return;

            parents[parentX] = parentY;
            numSets--;
        }

    }
}
