// 1710 https://leetcode.com/problems/maximum-units-on-a-truck/
public class MaximumUnitsOnTruck {
	public int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < boxTypes.length; i++) {
            pq.offer(new int[]{boxTypes[i][0], boxTypes[i][1]});
        }
        int maxUnits = 0;

        while (truckSize > 0 && !pq.isEmpty()) {
            int[] top = pq.poll();
            int maxUnitsTaken = Math.min(truckSize, top[0]);
            maxUnits += maxUnitsTaken * top[1];
            truckSize -= maxUnitsTaken;
        }
        return maxUnits;
    }
}
