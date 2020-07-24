package stack;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

//84 https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleHistogram {
    public static void main(String[] args) {
        LargestRectangleHistogram obj = new LargestRectangleHistogram();
        int[] heights = new int[100];
        for(int i = 0; i < 100; i++) {
            heights[i] = new Random().nextInt(10000);
        }
        System.out.println(obj.largestRectangleAreaBrute(heights));
        System.out.println(obj.largestRectangleArea(heights));
    }

    //Accepted Brute force (n^2)
    public int largestRectangleAreaBrute(int[] heights) {
        if(heights.length == 0) return 0;
        int maxArea = heights[0];

        for(int i = 1; i < heights.length; i++) {
            int height = heights[i];
            for(int j = i; j >= 0; j--) {
                height = Math.min(height, heights[j]);
                int area = height * (i - j + 1);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        final int n = heights.length;
        if (n == 0) return 0;

        int[] nearestSmallerToRight = getNearestSmallerToRight(heights);
        int[] nearestSmallerToLeft = getNearestSmallerToLeft(heights);

//        System.out.println(Arrays.toString(heights));
//        System.out.println(Arrays.toString(nearestSmallerToRight));
//        System.out.println(Arrays.toString(nearestSmallerToLeft));

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int width = 1;
            if (nearestSmallerToRight[i] == -1) {
                width += n - i - 1;
            } else {
                width += nearestSmallerToRight[i] - i - 1;
            }

            if (nearestSmallerToLeft[i] == -1) {
                width += i;
            } else {
                width += i - nearestSmallerToLeft[i] - 1;
            }


            int tempmaxArea = Math.max(height, (width * height));
            maxArea = Math.max(tempmaxArea, maxArea);
        }
        return maxArea;
    }

    private int[] getNearestSmallerToLeft(int[] heights) {
        int[] result = new int[heights.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                result[i] = stack.peek();
            }

            stack.push(i);
        }
        return result;
    }

    private int[] getNearestSmallerToRight(int[] heights) {
        int[] result = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                result[stack.pop()] = i;
            }

            stack.push(i);
        }
        return result;
    }


}
