import java.util.*;

// 241 https://leetcode.com/problems/different-ways-to-add-parentheses/
public class DifferentWaysToCompute {
    private Set<Character> operators = new HashSet<>(Arrays.asList('-', '+', '*'));

    public static void main(String[] args) {
        DifferentWaysToCompute obj = new DifferentWaysToCompute();
        String input = "1";
        System.out.println(obj.diffWaysToCompute(input));
    }

    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() == 0) {
            return Collections.emptyList();
        } else if (input.length() == 1) {
            return Collections.singletonList(Integer.parseInt(input));
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '-' || ch == '+' || ch == '*') {
                List<Integer> leftValues = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightValues = diffWaysToCompute(input.substring(i + 1));

                for (int val1 : leftValues) {
                    for (int val2 : rightValues) {
                        res.add(calculate(val1, val2, ch));
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }


    private int calculate(int val1, int val2, char ch) {
        switch (ch) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            default:
                return val1 * val2;
        }
    }
}
