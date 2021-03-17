import java.util.Random;

// 478 https://leetcode.com/problems/generate-random-point-in-a-circle/
class RandomPointCircle {
    public static void main(String[] args) {
        Solution2 obj = new Solution2(1, 0, 0);
    }

    static class Solution2 {

        double xCenter, yCenter;
        double radius;
        Random random;

        public Solution2(double radius, double x_center, double y_center) {
            this.xCenter = x_center;
            this.yCenter = y_center;
            this.radius = radius;

            random = new Random();

        }

        public double[] randPoint() {
            int low = 0;
            int high = (int) Math.floor(2 * Math.PI);
            double radians = low + (high - low) * random.nextDouble();

            double x = xCenter + radius * Math.cos(radians);
            double y = yCenter + radius * Math.sin(radians);
            return new double[]{x, y};
        }
    }
}
