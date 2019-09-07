package solutions;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistance {
    public static void main(String[] args) {
        ShortestDistance obj = new ShortestDistance();
        int[] x = obj.shortestToChar("loveleetcode", 'e');
        for(int i : x) {
            System.out.print(i + " ");
        }
    }

    public int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        List<Integer> indices = getIndices(S, C);

        for (int i = 0; i < S.length(); i++) {
            result[i] = difference(S, C, i, indices);
        }
        return result;
    }

    private int difference(String s, char c, int i, List<Integer> indices) {
        if (s.charAt(i) == c)
            return 0;
        else {
            int minDistance = Integer.MAX_VALUE;
            for (int idx : indices) {
                int diff = Math.abs(i - idx);
                minDistance = Math.min(diff, minDistance);
            }
            return minDistance;
        }
    }

    private List<Integer> getIndices(String S, char c) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == c) {
                indices.add(i);
            }
        }
        return indices;
    }
}
