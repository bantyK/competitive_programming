import java.util.*;
// 1273 https://leetcode.com/problems/delete-tree-nodes/
public class DeleteTreeNodes {
    public static void main(String[] args) {
        DeleteTreeNodes obj = new DeleteTreeNodes();
        int[] parent = new int[]{-1, 0, 0, 1, 2, 2, 2};
        int[] values = new int[]{1, -2, 4, 0, -2, -1, -1};
        int num = obj.deleteTreeNodes(7, parent, values);
        System.out.println(num);
    }

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        for (int i = nodes - 1; i > 0; i--) {
            value[parent[i]] += value[i];
        }
        HashSet<Integer> hs = new HashSet();
        int count = 0;
        for (int i = 0; i < nodes; i++) {
            if (hs.contains(parent[i])) {
                value[i] = 0;
            }
            if (value[i] == 0) {
                hs.add(i);
                count++;
            }
        }
        return nodes - count;
    }

}
