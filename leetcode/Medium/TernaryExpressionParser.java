import java.util.*;

//439 https://leetcode.com/problems/ternary-expression-parser/
public class TernaryExpressionParser {


    public String parseTernary(String expression) {
        Stack<Character> stack = new Stack<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);

            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop(); //pop ?

                char first = stack.pop();

                stack.pop(); // pop :

                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);

            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }

    public String parseTernaryRecursive(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        return helper(expression, 0, expression.length() - 1);
    }

    private String helper(String expr, int start, int end) {
        if (start == end) {
            return expr.charAt(start) + "";
        }
        int count = 0;
        int i = start;

        for (; i <= end; i++) {
            char c = expr.charAt(i);
            if (c == '?') {
                count++;
            } else if (c == ':') {
                count--;
                if (count == 0) {
                    //one branch can be evaluated
                    break;
                }
            }
        }
        return expr.charAt(start) == 'T' ?
                helper(expr, start + 2, i - 1) : helper(expr, i + 1, end);
    }

}
