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
        if dp[i]
         */
        boolean[] dp = new boolean[k + 1];

        for (int stones = 0; stones <= k; stones++) {
            for (int x : nums) {
                if (stones >= x && !dp[stones - x]) {
                    dp[stones] = true;
                    break;
                }
            }
        }

        System.out.println(dp[k] ? "First" : "Second");
    }

}
