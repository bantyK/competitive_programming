import java.util.*;

// 1202 https://leetcode.com/problems/smallest-string-with-swaps/
public class SmallestStringWithSwap {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] parent = new int[s.length()];

        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (List<Integer> pair : pairs) {
            union(parent, pair.get(0), pair.get(1));
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < parent.length; i++) {
            int p = find(parent, i);
//            System.out.println("parent of " + i + " is : " +p);

            map.putIfAbsent(p, new ArrayList<>());
            map.get(p).add(i);
        }

        char[] c = new char[s.length()];
        for(int index : map.keySet()) {
            List<Character> temp = new ArrayList<>();
            final List<Integer> indices = map.get(index);
            for(int i : indices) {
                temp.add(s.charAt(i));
            }

            Collections.sort(temp);
            for(int i = 0; i < indices.size(); i++) {
                c[indices.get(i)] = temp.get(i);
            }
        }

        return new String(c);
    }

    public void union(int[] parent, int nodeA, int nodeB) {
        int p1 = find(parent, nodeA);
        int p2 = find(parent, nodeB);
        if (p1 != p2) parent[p1] = p2;
    }

    public int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent, parent[node]);
    }
}
