import java.util.*;

// 1197 https://leetcode.com/problems/minimum-knight-moves/
public class KnightMovement {

    public static void main(String[] args) {

    }

    public int minKnightMoves(int x, int y) {
        // Taking advantage of symmetry
        // In questions of infinite board, consider the symmetric nature once
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer(new int[]{0, 0});
        seen.add(0 + "," + 0);
        int steps = 0;

        int[][] directions = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();

                if (curr[0] == x && curr[1] == y) {
                    return steps;
                }

                for (int[] dir : directions) {
                    int newI = curr[0] + dir[0];
                    int newJ = curr[1] + dir[1];
                    if (newI < -1 || newJ < -1 || seen.contains(newI + "," + newJ)) {
                        continue;
                    }
                    queue.offer(new int[]{newI, newJ});
                    seen.add(newI + "," + newJ);
                }
            }
            steps++;
        }

        return -1;//This wont occur according to given constraints of problem
    }


}
