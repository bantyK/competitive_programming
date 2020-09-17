package atcoder;

import java.util.*;

// https://atcoder.jp/contests/dp/tasks/dp_i
public class CoinsProbability {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        double[] probs = new double[n];
        String[] parts = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            probs[i] = Double.parseDouble(parts[i]);
        }

        // dp[i][j] represent the probability of getting j coins by tossing i coins
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 1; // probability of getting 0 heads when 0 coins are tossed is 1

        // 0th column represent the probability of getting 0 heads, so there is only one possibility which is getting tails.
        // the probability of getting tails in ith toss is 1 - p[i].
        // since coins are tossed one after another the probabilities will get multiplied
        for (int r = 1; r <= n; r++) {
            dp[r][0] = dp[r - 1][0] * (1 - probs[r - 1]);
        }

        // when we toss a coin, the outcome could be head or tail
        // based on the above fact, in order to fulfil our assumption of getting j heads in i tosses, there are only two possibilities.
        // 1. we get a tail, that means we must have j heads in (i - 1) toss dp[i-1][j]
        // 2. we get a head, that means we must (j - 1) heads in i - 1 tosses. dp[i - 1][j - 1]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i - 1][j] * (1 - probs[i - 1])) + (dp[i - 1][j - 1] * probs[i - 1]);
            }
        }

        // we must have more heads, that means we wil consider only those i values,
        double res = 0;
        for (int i = n / 2 + 1; i <= n; i++) {
            res += dp[n][i];
        }

        System.out.println(res);
    }
}
