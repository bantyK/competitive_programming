import java.util.*;

//946 https://leetcode.com/problems/validate-stack-sequences/
public class ValidateStackSequences {
    public static void main(String[] args) {
        ValidateStackSequences obj = new ValidateStackSequences();

        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
        System.out.println(obj.validateStackSequences(new int[]{4, 0, 1, 2, 3}, new int[]{4, 2, 3, 0, 1}));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        if (pushed.length == 0) return popped.length == 0;

        if (pushed.length != popped.length) return false;

        int popIndex = 0;
        int pushIndex = 1;

        stack.push(pushed[0]);

        while (!stack.isEmpty() || popIndex < popped.length) {
            if (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                ++popIndex;
            } else {
                if (pushIndex == pushed.length) return false;
                stack.push(pushed[pushIndex]);
                ++pushIndex;
            }
        }

        return true;
    }
}