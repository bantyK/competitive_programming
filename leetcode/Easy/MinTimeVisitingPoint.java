//1266: https://leetcode.com/problems/minimum-time-visiting-all-points/
public class MinTimeVisitingPoint {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1,1},{3,4},{-1,0}};
        int time = minTimeToVisitAllPointsBetter(points);
        System.out.println(time);
    }
    
    public static int minTimeToVisitAllPoints(int[][] points) {
        int minTime = 0;
        if(points == null || points.length <= 1) return minTime;
        
        int[] from, to;
        int x1, y1, x2, y2;

        for(int i = 0; i < points.length - 1; i++) {
            from = points[i];
            to = points[i + 1];

            x1 = from[0];
            y1 = from[1];
            x2 = to[0];
            y2 = to[1];
            
            System.out.println(x1 + "," + y1 + "," + x2 + "," + y2);
            while(x1 != x2 || y1 != y2) {
                // System.out.println(x1 + "," + y1);
                if(x2 > x1 && y2 > y1) {
                    x1 += 1;
                    y1 += 1;
                } else if(x2 < x1 && y2 < y1) {
                    x1 -= 1;
                    y1 -= 1;
                } else if(x2 == x1 && y2 > y1) {
                    y1 += 1;
                } else if(x2 == x1 && y2 < y1) {
                    y1 -= 1;
                } else if(x2 > x1 && y1 == y2) {
                    x1 += 1;
                } else if(x1 > x2 && y1 == y2) {
                    x1 -= 1;
                }
                minTime += 1;
                // System.out.println(x1 + "," + y1);
            }
        }
        return minTime;
    }

    public static int minTimeToVisitAllPointsBetter(int[][] points) {
        int minTime = 0;
        if(points == null || points.length <= 1) return minTime;

        int[] from, to;
        int x1, y1, x2, y2;
        for(int i = 0; i < points.length - 1; i++) {
            from = points[i];
            to = points[i + 1];

            x1 = from[0];
            y1 = from[1];
            x2 = to[0];
            y2 = to[1];

            minTime += Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        }

        return minTime;
    }
}