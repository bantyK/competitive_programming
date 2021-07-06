import java.util.PriorityQueue;

// 1921 https://leetcode.com/problems/eliminate-maximum-number-of-monsters/
public class EliminateMonsters {

    public static void main(String[] args) {
        EliminateMonsters obj = new EliminateMonsters();
        System.out.println(obj.eliminateMaximum(new int[]{3, 2, 4}, new int[]{5, 3, 2}));
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        // kill that monster first which is reaching the city at the earliest
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(dist[i] * (1.0 / speed[i]));
        }

        int count = 0;
        int secondsElapsed = 0;
        while (!pq.isEmpty()) {
            double timeRequiredByMonsterToReachCity = pq.poll();
            if (timeRequiredByMonsterToReachCity <= secondsElapsed) {
                return count;
            }
            secondsElapsed++;
            count++;
        }
        return count;
    }
}