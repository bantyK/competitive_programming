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
