package atcoder;

import java.util.*;
//https://atcoder.jp/contests/dp/tasks/dp_c
public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] points = new int[n][3];

        for(int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            points[i][0] = Integer.parseInt(line[0]);
            points[i][1] = Integer.parseInt(line[1]);
            points[i][2] = Integer.parseInt(line[2]);
        }

        int maxScore = 0;
        for(int i = 0; i < 3; i++) {
            maxScore = Math.max(maxScore, points[0][i]);
        }

        for(int r = 1; r < n; r++) {
            points[r][0] += Math.max(points[r-1][1], points[r-1][2]);
            points[r][1] += Math.max(points[r-1][0], points[r-1][2]);
            points[r][2] += Math.max(points[r-1][0], points[r-1][1]);
        }

        for(int i = 0; i < 3; i++) {
            maxScore = Math.max(maxScore, points[n - 1][i]);
        }
        System.out.println(maxScore);
    }
}
