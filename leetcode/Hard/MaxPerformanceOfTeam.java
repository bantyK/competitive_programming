import java.util.*;

//1383 https://leetcode.com/problems/maximum-performance-of-a-team/
public class MaxPerformanceOfTeam {
    public static void main(String[] args) {
        MaxPerformanceOfTeam obj = new MaxPerformanceOfTeam();
        System.out.println(obj.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 2));
        System.out.println(obj.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 3));
        System.out.println(obj.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 4));
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<Engineer> engineers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            engineers.add(new Engineer(efficiency[i], speed[i]));
        }

        // sorting this wrt efficiency, so that when we traverse the list, the newest item will always
        // be the one with min efficiency. This gives the engineer with lowest efficiency in constant time.
        engineers.sort((e1, e2) -> e2.efficiency - e1.efficiency);

        // The engineer with slowest speed will be evicted first
        PriorityQueue<Engineer> pq = new PriorityQueue<>((e1, e2) -> e1.speed - e2.speed);
        int teamSize = 0;
        long maxPerformance = 0;
        long currentSpeedSum = 0;

        for (Engineer newHire : engineers) {
            if (teamSize >= k) {
                Engineer slowestEngineer = pq.poll();
                teamSize--;
                currentSpeedSum -= slowestEngineer.speed;
            }

            currentSpeedSum += newHire.speed;
            teamSize++;
            pq.offer(newHire);

            // new hire's efficiency is going to be lowest because
            // engineers are sorted in ascending orderr based on efficiency
            maxPerformance = Math.max(maxPerformance, currentSpeedSum * newHire.efficiency);
        }

        return (int) (maxPerformance % 100000007);
    }

    static class Engineer {
        int efficiency;
        int speed;

        public Engineer(int efficiency, int speed) {
            this.efficiency = efficiency;
            this.speed = speed;
        }
    }
}