package atcoder;

//https://atcoder.jp/contests/dp/tasks/dp_h

import java.util.*;

public class AllPaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(line[0]);
        int cols = Integer.parseInt(line[1]);

        char[][] matrix = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
        }

        long[][] dp = new long[rows][cols];
        dp[0][0] = 1;
        int c = 0;
        while (c < cols && matrix[0][c] != '#') {
            dp[0][c] = 1;
            c++;
        }

        int r = 0;
        while (r < rows && matrix[r][0] != '#') {
            dp[r][0] = 1;
            r++;
        }

        for (r = 1; r < rows; r++) {
            for (c = 1; c < cols; c++) {
                if (matrix[r][c] != '#') {
                    dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % 1000000007;
                }
            }
        }
        System.out.println((dp[rows - 1][cols - 1]));
    }
}
