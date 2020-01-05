import java.util.*;

// 150 https://leetcode.com/problems/evaluate-reverse-polish-notation/
public class EvaluateExpression {
    public static void main(String[] args) {
        EvaluateExpression obj = new EvaluateExpression();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a;
        int b;
        for (String token : tokens) {
            if (isOperator(token)) {
                b = stack.pop();
                a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}
