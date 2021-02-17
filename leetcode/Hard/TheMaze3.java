import java.util.*;

//499 https://leetcode.com/problems/the-maze-iii/
public class TheMaze3 {
    public static void main(String[] args) {
        TheMaze3 obj = new TheMaze3();
        int[][] maze = new int[][] { { 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
                { 0, 1, 0, 0, 0 } };
        int[] ball = new int[] { 4, 3 };
        int[] hole = new int[] { 0, 1 };

        System.out.println(obj.findShortestWay(maze, ball, hole));
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int rows = maze.length;
        int cols = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] bestDistances = new int[rows][cols];
        String[][] bestPath = new String[rows][cols];

        int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] labels = new String[] { "d", "u", "r", "l" };
        for (int i = 0; i < rows; i++) {
            Arrays.fill(bestDistances[i], Integer.MAX_VALUE);
            Arrays.fill(bestPath[i], "z"); // highest value , should be something greater than l, r, u and d
        }

        bestDistances[ball[0]][ball[1]] = 0;
        bestPath[ball[0]][ball[1]] = "";

        queue.offer(new int[] { ball[0], ball[1] });

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = curr[0];
                int y = curr[1];
                int distance = bestDistances[x][y];
                String currentPath = bestPath[x][y];
                currentPath += labels[i];
                // go into a direction until a wall is found or reach a boundary
                while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] == 0) {
                    if (x == hole[0] && y == hole[1]) {
                        // if while moving we drop into the hole, no need to go forward, break from here
                        break;
                    }
                    x += directions[i][0];
                    y += directions[i][1];
                    distance += 1;
                }

                // Not in hole
                // If not in hole, while loop takes one step ahead. take back 1 step and reduce
                // the step count also
                if (x != hole[0] || y != hole[1]) {
                    x -= directions[i][0];
                    y -= directions[i][1];
                    distance -= 1;
                }

                if (x == curr[0] && y == curr[1]) {
                    // reached to the same place
                    continue;
                }

                if (distance <= bestDistances[x][y]) {
                    if (distance < bestDistances[x][y]) {
                        bestDistances[x][y] = distance;
                        bestPath[x][y] = currentPath;
                    } else if (currentPath.compareTo(bestPath[x][y]) < 0) {
                        // the distance is same as bestDistances[x][y] but path is lexicographically
                        // smaller
                        // update the path
                        bestPath[x][y] = currentPath;
                    }

                    queue.offer(new int[] { x, y });
                }
            }
        }
        return bestDistances[hole[0]][hole[1]] != Integer.MAX_VALUE ? bestPath[hole[0]][hole[1]] : "impossible";
    }

}