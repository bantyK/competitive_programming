import java.util.*;

// 218 https://leetcode.com/problems/the-skyline-problem/
public class SkylineProblem {

    public static void main(String[] args) {
        int[][] buildings = new int[][]{
                {1, 3, 3},
                {2, 4, 4},
                {5, 8, 2},
                {6, 7, 4},
                {8, 9, 4}
        };

        SkylineProblem obj = new SkylineProblem();
        System.out.println(obj.getSkyline(buildings));
    }

    private List<List<Integer>> getSkyline(int[][] buildings) {
        return getSkylineUsingTreeMap(buildings);
    }


    // Priority Queue solution
    // Because of remove operation which is linear in complexity, the overall time complexity goes up to (n2)
    // This can be reduced to NlogN using a TreeMap, look below
    public List<List<Integer>> getSkylinePriorityQueue(int[][] buildings) {
        int n = buildings.length;

        // Any change in the final answer will happen either at the start or at the end of a building,
        // hence both end and stat points need to be considered.
        Point[] points = new Point[n * 2];
        int i = 0;

        for (int[] building : buildings) {
            int start = building[0];
            int end = building[1];
            int height = building[2];

            points[i++] = new Point(start, height, true);
            points[i++] = new Point(end, height, false);
        }

        // The points need to sorted by x position first.
        // If the x positions are same, then 3 cases arise
        // 1. The start of both buildings are same, choose the building with greater height
        // 2. The end of both building are same, choose the building with lower height
        // 3. One is start and the other is end of two building, choose the building with greater height

        Arrays.sort(points);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        List<List<Integer>> res = new ArrayList<>();
        int prevMaxHeight = 0;
        queue.offer(0);

        for (Point point : points) {
            if (point.isStart) {
                queue.offer(point.height);
                if (prevMaxHeight != queue.peek()) {
                    prevMaxHeight = queue.peek();
                    res.add(Arrays.asList(point.x, point.height));
                }
            } else {
                queue.remove(point.height);
                if (prevMaxHeight != queue.peek()) {
                    res.add(Arrays.asList(point.x, queue.peek()));
                }
                prevMaxHeight = queue.peek();
            }
        }

        return res;
    }

    public List<List<Integer>> getSkylineUsingTreeMap(int[][] buildings) {
        int n = buildings.length;

        // Any change in the final answer will happen either at the start or at the end of a building,
        // hence both end and stat points need to be considered.
        Point[] points = new Point[n * 2];
        int i = 0;

        for (int[] building : buildings) {
            int start = building[0];
            int end = building[1];
            int height = building[2];

            points[i++] = new Point(start, height, true);
            points[i++] = new Point(end, height, false);
        }

        Arrays.sort(points);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        List<List<Integer>> res = new ArrayList<>();
        int prevMaxHeight = 0;
        treeMap.put(0, 0);

        for (Point point : points) {
            if (point.isStart) {
                // incrementing the count of height in the treemap
                treeMap.compute(point.height, (height, count) -> {
                    if (count == null) {
                        return 1;
                    } else {
                        return count + 1;
                    }
                });
            } else {
                // decrementing the count of height in the treemap
                treeMap.compute(point.height, (height, count) -> {
                    if (count == 1) {
                        return null; // remove this entry from the treeMap if its count was 1
                    }
                    return count - 1; // else decrement its height
                });
            }
            // get the highest building from the treeMap
            int currentMaxHeight = treeMap.lastKey();
            // if the current heighest and previous highest are not same, then add that in the final result
            if (currentMaxHeight != prevMaxHeight) {
                res.add(Arrays.asList(point.x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }

        return res;
    }

    static class Point implements Comparable<Point> {
        int x;
        int height;
        boolean isStart;

        public Point(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) {
                return this.x - other.x;
            }
            if (this.isStart && other.isStart) {
                // both points are start of 2 buildings
                return other.height - this.height;
            } else if (!this.isStart && !other.isStart) {
                // both points are end of 2 building
                return this.height - other.height;
            } else {
                // one is start and other is end
                // then the nextBuilding's start should be considered first
                return isStart ? -1 : 1;
            }
        }

        @Override
        public String toString() {
            return "{x=" + x +
                    ", height=" + height +
                    ", isStart=" + isStart +
                    "}\n";
        }
    }
}