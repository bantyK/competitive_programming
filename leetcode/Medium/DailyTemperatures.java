package solutions.medium;

import solutions.util.ArrayUtils;

import java.util.*;
// 739 https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures obj = new DailyTemperatures();
        ArrayUtils.printArray(obj.dailyTemperaturesBetter(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Arrays.fill(result, 0);

        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    public int[] dailyTemperaturesBetter(int[] T) {
        int[] result = new int[T.length];
        if (T == null || T.length == 0) return result;
        Arrays.fill(result, 0);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return result;
    }
}
