import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//947  https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class RemoveStones {
    public static void main(String[] args) throws IOException {
        RemoveStones obj = new RemoveStones();
        int[][] dArray = new TwoDArrayReader().get2DArray();
        System.out.println(obj.findCircleNum(dArray));

    }

    public int findCircleNum(int[][] stones) {
        int unions = 0;
        Map<String, String> parent = new HashMap<>();

        for (int[] stone : stones) {
            String s = stone[0] + " " + stone[1];
            parent.put(s, s);
        }

        for (int[] s1 : stones) {
            String ss1 = s1[0] + " " + s1[1];
            for (int[] s2 : stones) {
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    String ss2 = s2[0] + " " + s2[1];
                    if (union(parent, ss1, ss2)) {
                        unions += 1;
                    }
                }
            }
        }

        return unions;
    }

    private boolean union(Map<String, String> parent, String ss1, String ss2) {
        String p1 = find(parent, ss1);
        String p2 = find(parent, ss2);

        if (p1.equals(p2)) return false;
        parent.put(p1, p2);
        return true;
    }

    private String find(Map<String, String> parent, String s) {
        if (parent.get(s).equals(s)) return parent.get(s);
        else {
            parent.put(s, find(parent, parent.get(s)));
            return parent.get(s);
        }
    }
}
