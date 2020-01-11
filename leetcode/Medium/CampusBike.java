import java.util.*;

// 1057 https://leetcode.com/problems/campus-bikes/
public class CampusBike {
    public static void main(String[] args) {
        CampusBike obj = new CampusBike();

        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};

        System.out.println(Arrays.toString(obj.assignBikes2(workers, bikes)));
    }

    public int[] assignBikes2(int[][] workers, int[][] bikes) {
        List<int[]>[] buckets = new List[2001];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (buckets[dist] == null) {
                    buckets[dist] = new ArrayList<>();
                }
                buckets[dist].add(new int[]{i, j});
            }
        }

        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> assignedBike = new HashSet<>();

        for (int i = 0; i < buckets.length && assignedBike.size() < workers.length; i++) {
            if (buckets[i] != null) {
                for (int[] pair : buckets[i]) {
                    if (res[pair[0]] < 0 && !assignedBike.contains(pair[1])) {
                        res[pair[0]] = pair[1];
                        assignedBike.add(pair[1]);
                    }
                }
            }
        }

        return res;
    }

    /**
     * Solution using priority queue
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int w = workers.length;
        int b = bikes.length;

        int[] wo = new int[w];
        int[] bi = new int[b];
        Arrays.fill(wo, -1);
        Arrays.fill(bi, -1);
        ;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) return a[0] - b[0]; // first compare against distance
                else if (a[1] != b[1]) return a[1] - b[1]; // if distances are same, use the worker index
                else return a[2] - b[2]; // if distance and worker indices are same, use bike index
            }
        });

        for (int workerIndex = 0; workerIndex < w; workerIndex++) {
            for (int bikeIndex = 0; bikeIndex < b; bikeIndex++) {
                int[] worker = workers[workerIndex];
                int[] bike = bikes[bikeIndex];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                pq.offer(new int[]{dist, workerIndex, bikeIndex});
            }
        }

        int assigned = 0;
        while (!pq.isEmpty() && assigned < w) {
            int[] entry = pq.poll();
            int workerIndex = entry[1];
            int bikeIndex = entry[2];

            // we should be dealing with the workers who are not assigned any bike and the bike which are not assigned yet
            if (wo[workerIndex] == -1 && bi[bikeIndex] == -1) {
                wo[workerIndex] = bikeIndex; // the worker at workerIndex is assigned a bike at bikeIndex
                bi[bikeIndex] = workerIndex; // the bike at bikeIndex is assigned to worker at workerIndex
                assigned++;
            }
        }

        return wo;
    }
}
