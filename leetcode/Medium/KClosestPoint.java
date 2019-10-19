package solutions.medium;

import java.util.*;

public class KClosestPoint {
    public static void main(String[] args) {
        KClosestPoint obj = new KClosestPoint();

        int[][] points = obj.kClosest(new int[][]{{0, 1}, {1, 0}}, 2);

        for (int[] point : points) {
            System.out.println(point[0] + ", " + point[1]);
        }
    }

    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] p1, int[] p2) {
            double d1 = distanceFromOrigin(p1);
            double d2 = distanceFromOrigin(p2);
            return Double.compare(d1, d2);
        }
    };

    public int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][2];
        Map<String, int[]> map = new HashMap<>();
        PriorityQueue<int[]> distanceHeap = new PriorityQueue<>(comparator);

        distanceHeap.addAll(Arrays.asList(points));

        for (int i = 0; i < K; i++) {
            result[i] = distanceHeap.remove();
        }
        return result;
    }


    private double distanceFromOrigin(int[] point) {
        return Math.sqrt((point[0] * point[0]) + (point[1] * point[1]));
    }


}
