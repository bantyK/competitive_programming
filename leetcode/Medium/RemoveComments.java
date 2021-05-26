import java.util.*;

// 722 https://www.leetcode.com/problems/remove-comments
public class RemoveComments {

    public static void main(String[] args) {
        RemoveComments obj = new RemoveComments();
        String[] code = new String[]{"struct Node{", "    /*/ declare members;/*", "*/   ", "    int size;", "    /**/int val;", "};"};
        System.out.println(obj.removeComments(code));
    }

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean insideBlockCommentMode = false;
        StringBuilder sb = new StringBuilder();
        for (String s : source) {
            int n = s.length();

            for (int i = 0; i < s.length(); i++) {
                if (insideBlockCommentMode) {
                    if (s.charAt(i) == '*' && i < n - 1 && s.charAt(i + 1) == '/') {
                        insideBlockCommentMode = false;
                        i++;
                    }
                } else {
                    if (s.charAt(i) == '/' && i < n - 1 && s.charAt(i + 1) == '/') {
                        break; // ignore all other characters in the current line
                    } else if (s.charAt(i) == '/' && i < n - 1 && s.charAt(i + 1) == '*') {
                        insideBlockCommentMode = true;
                        i++;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }

            if (!insideBlockCommentMode && sb.length() > 0) {
                res.add(sb.toString());
                sb.setLength(0);
            }
        }

        return res;
    }
}