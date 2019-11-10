package solutions.medium;

// https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater obj = new ContainerWithMostWater();

        int area = obj.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(area);
    }

    public int maxArea(int[] height) {
        int maxArea = 0;

        if (height.length == 0) return maxArea;

        int j = height.length - 1;
        int i = 0;
        int area = 0;
        while (j > i) {
            area = Math.min(height[i], height[j]) * (j - i);
            if (area > maxArea) maxArea = area;

            if (height[i] > height[j]) j--;
            else i++;
        }

        return maxArea;
    }
}
