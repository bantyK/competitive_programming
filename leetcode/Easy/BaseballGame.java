import java.util.*;
// 682 https://leetcode.com/problems/baseball-game/
public class BaseballGame {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for(String op : ops) {
            switch(op) {
                case "C":
                    stack.pop();
                    break;
                case "+":
                    int first = stack.pop();
                    int second = stack.pop();
                    int sum = first + second;
                    stack.push(second);
                    stack.push(first);
                    stack.push(sum);
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }
        
        int sum = 0;
        while(!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}