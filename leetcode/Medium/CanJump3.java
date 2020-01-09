import java.util.*;

// 1306 https://leetcode.com/problems/jump-game-iii/
public class CanJump3 {
    public static void main(String[] args) {
        CanJump3 obj = new CanJump3();

        int[] arr = {4,2,3,0,3,1,2};
        int start = 0;
        System.out.println(obj.canReach(arr, start));
    }

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (arr[curr] == 0) return true;

            visited[curr] = true;

            int forward = curr + arr[curr];
            if (forward < arr.length && !visited[forward]) {
                queue.offer(forward);
            }

            int backward = curr - arr[curr];
            if (backward >= 0 && !visited[backward]) {
                queue.offer(backward);
            }
        }

        return false;
    }
}
