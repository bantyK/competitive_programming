import java.util.*;
// 394 : https://leetcode.com/problems/decode-string/
public class DecodeString {

    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stack = new Stack<>();

        int idx = 0;

        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == ']') {
                StringBuilder builder = new StringBuilder();

                String pop = stack.pop();
                while (!pop.equals("[")) {
                    builder.insert(0, pop);
                    pop = stack.pop();
                }

                int t = countStack.pop();
                for (int i = 0; i < t; i++) {
                    stack.push(builder.toString());
                }
                idx++;

            } else {
                stack.push(s.charAt(idx) + "");
                idx++;
            }



        }

        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }

        return builder.toString();
    }
}