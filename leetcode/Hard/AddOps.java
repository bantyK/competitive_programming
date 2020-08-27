import java.util.*;

// 282 https://leetcode.com/problems/expression-add-operators/
public class AddOps {

    public static void main(String[] args) {
        AddOps obj = new AddOps();
        System.out.println(obj.addOperators("105", 5));
    }

    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) return Collections.emptyList();
        List<String> result = new ArrayList<>();
        dfs(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * @param result: List to store all the valid expressions
     * @param num     input string
     * @param target  expression value given in the question
     * @param expr    current expression string
     * @param calcVal current calculation value
     * @param preNum  previous number, in order to multiply current number if we want to put * between preNum and currNum
     * @param pos     current index in the input num string
     */
    public void dfs(List<String> result, String num, int target, String expr, long calcVal, long preNum, int pos) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = pos; i < num.length(); i++) {
            // this means that the currently we are processing a number and it has a 0 at the start,
            // this is an invalid condition and no need to go this path.
            // pos is the variable from which this recursion started and i is after pos.
            // if i has passed the start index (which is pos in this case) so we are processing the number
            // at index greater than start, we have passed start.
            // now we cannot have a zero before the current index (05 for example)
            if (i > pos && num.charAt(pos) == '0') {
                break;
            }

            long currentNum = Long.parseLong(num.substring(pos, i + 1));

            if (pos == 0) {
                dfs(result, num, target, expr + currentNum, currentNum, currentNum, i + 1);
            } else {
                dfs(result, num, target, expr + "+" + currentNum, calcVal + currentNum, currentNum, i + 1);
                dfs(result, num, target, expr + "-" + currentNum, calcVal - currentNum, -currentNum, i + 1);
                // first subtract the previous value then append the multiplication value
                // this is the reason we are also passing the current calculated value in recursion calls so that we can use it in later call to fix the
                // multiplication problem
                // Check the comments here: https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
                dfs(result, num, target, expr + "*" + currentNum, calcVal - preNum + preNum * currentNum, preNum * currentNum, i + 1);
            }
        }
    }
}
