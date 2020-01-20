import java.util.*;

// 71 https://leetcode.com/problems/simplify-path/
public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath obj = new SimplifyPath();
        System.out.println(obj.simplifyPath("/a//b////c/d//././/.."));
        System.out.println(obj.simplifyPath("/a/./b/../../c/"));
        System.out.println(obj.simplifyPath("/a/../../b/../c//.//"));
        System.out.println(obj.simplifyPath("/home/"));
        System.out.println(obj.simplifyPath("/../"));
    }

    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";

        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String s : split) {
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (s.equals(".") || s.length() == 0) {
                continue;
            } else {
                stack.push(s);
            }
        }

        List<String> res = new ArrayList<>(stack);
        return "/" + String.join("/", res);
    }
}
