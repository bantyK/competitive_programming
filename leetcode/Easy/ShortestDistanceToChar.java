import java.util.*;

// 821 https://leetcode.com/problems/shortest-distance-to-a-character/
public class ShortestDistanceToChar {

    public static void main(String[] args) {
        ShortestDistanceToChar obj = new ShortestDistanceToChar();
        int[] res = obj.shortestToChar("loveleetcode", 'e');
        System.out.println(Arrays.toString(res));
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != c) {
                dp[i] = Integer.MAX_VALUE;
            }
        }

        // mark the distances from both sides and update the answer if the new distance
        // is less than previous calculated value

        // Going from left to right
        // these are the distances of every index with the char index which is to the
        // left of the index
        for (int i = 0; i < n - 1; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            dp[i + 1] = Math.min(dp[i + 1], 1 + dp[i]);
        }

        // going from right to left
        // these will be the distance from char index which is to the right of it
        for (int i = n - 1; i > 0; i--) {
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }

        return dp;
    }

    // Using Binary search
    public int[] shortestToChar2(String s, char c) {
        TreeSet<Integer> charIndexSet = new TreeSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                charIndexSet.add(i);
            }
        }
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            Integer floorIndex = charIndexSet.floor(i);
            int distance1 = Integer.MAX_VALUE, distance2 = Integer.MAX_VALUE;
            if (floorIndex != null) {
                distance1 = i - floorIndex;
            }
            Integer ceilingIndex = charIndexSet.ceiling(i);
            if (ceilingIndex != null) {
                distance2 = ceilingIndex - i;
            }

            res[i] = Math.min(distance1, distance2);
        }

        return res;
    }

}