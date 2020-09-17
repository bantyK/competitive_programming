package atcoder;

import java.util.*;

// https://atcoder.jp/contests/dp/tasks/dp_k
public class StoneGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int k = Integer.parseInt(parts[1]);

        int[] nums = new int[n];

        parts = scanner.nextLine().split(" ");
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        /*
        if dp[i] is true that means the first player which picks i stones will win.
         */
        boolean[] dp = new boolean[k + 1];

        for (int stones = 0; stones <= k; stones++) {
            for (int x : nums) {
                // stones - x means the remaining stones which remains after  picking x stones.
                // The other player has stones - x stones to pick now.
                // if dp[stones - x] is true that means the player which has to pick this many stones wins.
                // In this scenario it was the second player. Hence the first player who picked x stones before lost.
                // Vice versa, if the dp[stones - x] is false that means for second player who has to pick this many stones will lose
                // meaning the first player will win
                if (stones >= x && !dp[stones - x]) {
                    dp[stones] = true;
                    break;
                }
            }
        }

        System.out.println(dp[k] ? "First" : "Second");
    }

}
