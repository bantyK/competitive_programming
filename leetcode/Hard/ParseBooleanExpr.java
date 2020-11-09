package stack;

import java.util.*;

// 1106 https://leetcode.com/problems/parsing-a-boolean-expression/
public class ParseBooleanExpr {
    public static void main(String[] args) {
        ParseBooleanExpr obj = new ParseBooleanExpr();
        System.out.println(obj.parseBoolExpr("!(f)") == true);
        System.out.println(obj.parseBoolExpr("!(t)") == false);
        System.out.println(obj.parseBoolExpr("|(f,t)") == true);
        System.out.println(obj.parseBoolExpr("&(f,t)") == false);
        System.out.println(obj.parseBoolExpr("&(&(t,t),|(t,f))") == true);

    }

    public boolean parseBoolExpr(String expr) {
        Stack<Character> operators = new Stack<>();
        Stack<Boolean> operands = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == 't') {
                operands.push(true);
            } else if (ch == 'f') {
                operands.push(false);
            } else if (ch == '(') {
                operands.push(null);
            } else if (ch == ')') {
                char operator = operators.pop();
                boolean res = true;
                if (operator == '|') {
                    res = false;
                }

                while (!operands.isEmpty() && operands.peek() != null) {
                    if (operator == '|') {
                        res |= operands.pop();
                    } else if (operator == '&') {
                        res &= operands.pop();
                    } else if (operator == '!') {
                        res = !operands.pop();
                    }
                }
                operands.pop(); // pop null
                operands.push(res);
            } else if (ch == ',') {
                continue;
            } else {
                operators.push(ch);
            }
        }

        return operands.peek();
    }
}
