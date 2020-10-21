import java.util.*;

// 735 https://leetcode.com/problems/asteroid-collision/1
public class AstroidCollision {
    public static void main(String[] args) {
        AstroidCollision obj = new AstroidCollision();
        System.out.println(Arrays.toString(obj.asteroidCollision(new int[]{1, 2, 3, 4, -5})));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int num : asteroids) {
            if (num > 0) {
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(num)) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(num);
                } else if (stack.peek() == Math.abs(num)) {
                    stack.pop();
                }
            }
        }
        int[] res = new int[stack.size()];
        int idx = res.length - 1;
        while (!stack.isEmpty()) {
            res[idx--] = stack.pop();
        }
        return res;
    }
}
