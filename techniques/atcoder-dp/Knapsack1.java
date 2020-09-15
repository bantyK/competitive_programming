package atcoder;

import java.util.Scanner;
//https://atcoder.jp/contests/dp/tasks/dp_d
public class Knapsack1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int w = Integer.parseInt(line[1]);
        int[] weights = new int[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            line = scanner.nextLine().split(" ");
            weights[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
        }

        Long[][] dp = new Long[n][w + 1];
        System.out.println(knapsack(weights, values, 0, w, dp));
    }

    private static long knapsack(int[] weights, int[] values, int currentIndex, int remainingWeight, Long[][] dp) {
        if(remainingWeight <= 0) {
            return 0;
        }

        if (currentIndex >= weights.length) {
            return 0;
        }

        if (dp[currentIndex][remainingWeight] != null) {
            return dp[currentIndex][remainingWeight];
        }

        long choice1 = 0;
        if (weights[currentIndex] <= remainingWeight) {
            choice1 = values[currentIndex] + knapsack(weights, values, currentIndex + 1, remainingWeight - weights[currentIndex], dp);
        }

        long choice2 = knapsack(weights, values, currentIndex + 1, remainingWeight, dp);

        dp[currentIndex][remainingWeight] = Math.max(choice1, choice2);
        return dp[currentIndex][remainingWeight];
    }
}
