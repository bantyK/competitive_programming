import java.util.Arrays;

//354 https://leetcode.com/problems/russian-doll-envelopes/
public class RussianDollsEnvelopes {
    public static void main(String[] args) {
        RussianDollsEnvelopes obj = new RussianDollsEnvelopes();

        System.out.println(obj.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(obj.maxEnvelopes(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}}));
        System.out.println(obj.maxEnvelopes(new int[][]{{1, 2}, {2, 1}}));
    }

    public int maxEnvelopes(int[][] envelopes) {
        // sort the numbers based on widths
        Arrays.sort(envelopes, (i1, i2) -> {
            if (i1[0] == i2[0]) return i1[1] - i2[1];
            return i1[0] - i2[0];
        });
        int[] dp = new int[envelopes.length];
        int count = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                // both the width and height must be strictly increasing
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = 1 + max;
            count = Math.max(count, dp[i]);
        }
        return count;
    }
}
