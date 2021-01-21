import java.util.*;

// 856 https://leetcode.com/problems/score-of-parentheses/
public class ParenthesisScore {
    public static void main(String[] args) {
        ParenthesisScore obj = new ParenthesisScore();
        System.out.println(obj.scoreOfParentheses("()()") == 2);
        System.out.println(obj.scoreOfParentheses("(()())") == 4);
        System.out.println(obj.scoreOfParentheses("()(()()())") == 7);
        System.out.println(obj.scoreOfParentheses("()(()()())()") == 8);
        System.out.println(obj.scoreOfParentheses("(()(()()())())") == 16);
    }

    public int scoreOfParentheses(String S) {
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == '(') {
                nums.push(-1); // a delimiter
            } else {
                int curr = 0;
                while (nums.peek() != -1) {
                    // summing all the valid parenthesis enclosed within a parent paranthesis
                    curr += nums.pop();
                }
                nums.pop(); // pop the delimiter
                if (curr == 0) {
                    nums.push(1); // there is only pair of parenthesis ()
                } else {
                    // there are multiple pairs of paranthesis
                    // (()()) -> curr = 2 for 2 pairs inside
                    // this will be double because both the pairs are enclosed inside
                    // another parent pair
                    nums.push(2 * curr);
                }
            }
        }

        int total = 0;
        while (!nums.isEmpty())
            total += nums.pop();

        return total;
    }
}