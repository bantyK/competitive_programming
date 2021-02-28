import java.util.*;

//1481 https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
public class LeastUniqueIntegersAfterKRemovals {

    public static void main(String[] args) {
        LeastUniqueIntegersAfterKRemovals obj = new LeastUniqueIntegersAfterKRemovals();
        System.out.println(obj.findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));

    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // {num, count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int key : count.keySet()) {
            pq.add(new int[]{key, count.get(key)});
        }


        while (!pq.isEmpty() && k > 0 && pq.peek()[1] <= k) {
            int[] curr = pq.poll();
            int c = curr[1];
            int diff = Math.min(k, c);
            c -= diff;
            k -= diff;
            if (c > 0) {
                pq.offer(new int[]{curr[0], c});
            }
        }

        return pq.size();
    }
}
