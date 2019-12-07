// 22 https://leetcode.com/problems/generate-parentheses/

import java.util.*;

public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        int n = 3;
        List<String> res = obj.generateParenthesis(2);

        System.out.println(res);
    }


    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recurse(n, n, res, "");
        return res;
    }


    private void recurse(int openCount, int closeCount, List<String> result, String s) {
        if (openCount == 0 && closeCount == 0) {
            result.add(s);
            return;
        }

        if (openCount > 0) {
            recurse(openCount - 1, closeCount, result, s + "(");
        }

        if (openCount < closeCount) {
            recurse(openCount, closeCount - 1, result, s + ")");
        }
    }

    // Brute force
    public List<String> generateParenthesis2(int n) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set = helper(i, set);
        }
        return new ArrayList<>(set);
    }

    private Set<String> helper(int n, Set<String> previous) {
        Set<String> set = new HashSet<String>();
        if (previous.size() == 0) {
            set.add("()");
            return set;
        } else {
            StringBuilder builder;
            for (String s : previous) {
                // add a pair of parenthesis convering the initial string
                builder = new StringBuilder();
                String temp = "(";
                temp += s;
                temp += ")";
                set.add(temp);

                // add a pair of parenthesis at the beginnning of the initial string
                temp = s;
                temp += "()";
                set.add(temp);

                // add a pair of parenthesis at the end of the initial string
                temp = "()";
                temp += s;
                set.add(temp);
            }

            return set;
        }

    }
}