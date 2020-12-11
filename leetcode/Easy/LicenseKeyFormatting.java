import java.util.Stack;

// 482 https://leetcode.com/problems/license-key-formatting/
public class LicenseKeyFormatting {

    public static void main(String[] args) {
        LicenseKeyFormatting obj = new LicenseKeyFormatting();
        // System.out.println(obj.licenseKeyFormatting("2-5g-3-J", 2));
        // System.out.println(obj.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(obj.licenseKeyFormatting("------a-a-a-a--", 2));
    }

    public String licenseKeyFormatting(String S, int K) {
        Stack<Character> stack = new Stack<>();
        int charCount = 0;
        for(int i = S.length() - 1; i >= 0; i--) {
            char ch = S.charAt(i);
            if(ch != '-') {
                stack.push(Character.toUpperCase(ch));
                if(i > 0 && ++charCount == K) {
                    stack.push('-');
                    charCount = 0;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        String res = builder.toString();
        if(res.length() == 0) return "";
        return res.charAt(0) == '-' ? res.substring(1) : res;
    }
}
