import java.util.*;

// 1824 https://leetcode.com/problems/minimum-sideway-jumps/
public class MinSideJumps {
    public static void main(String[] args) {
        MinSideJumps obj = new MinSideJumps();
        System.out.println(obj.minSideJumps(new int[]{0, 1, 2, 3, 0}));
    }

    public int minSideJumps(int[] obstacles) {
        return helper(obstacles.length, obstacles, 0, 2, new HashMap<>());
    }

    private int helper(int n, int[] obstacles, int point, int lane, Map<String, Integer> cache) {
        while (point < n && obstacles[point] != lane) {
            // keep moving straight, until an obstacle is found
            point += 1;
        }

        point -= 1;

        if (point == n - 1) {
            return 0;
        }

        String key = point + "." + lane;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int ans = Integer.MAX_VALUE;

        for (int newLane = 1; newLane <= 3; newLane++) {
            if (obstacles[point] != newLane && obstacles[point + 1] != newLane) {
                ans = Math.min(ans, 1 + helper(n, obstacles, point + 1, newLane, cache));
            }
        }

        cache.put(key, ans);
        return ans;
    }
}