import java.util.Arrays;
// 42 https://leetcode.com/problems/trapping-rain-water/
public class RainWaterTrapping {
    public static void main(String[] args) {
        RainWaterTrapping obj = new RainWaterTrapping();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(obj.trap(height));
    }

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        System.out.println(Arrays.toString(height));
        int[] left = new int[n];
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        System.out.println(Arrays.toString(left));
        int[] right = new int[n];
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }
        System.out.println(Arrays.toString(right));

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.min(left[i], right[i]) - height[i];
        }

        return total;
    }
}
