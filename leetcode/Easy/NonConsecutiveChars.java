//1576  https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters
public class NonConsecutiveChars {

    public static void main(String[] args) {
        System.out.println(new NonConsecutiveChars().modifyString("???"));
    }

    public String modifyString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        for(int i = 0; i < n; i++) {
            if(chars[i] == '?') {
                for(int j = 0; j < 3; j++) {
                    if(i > 0 && chars[i - 1] - 'a' == j) continue;
                    if(i < n - 1 && chars[i + 1] - 'a' == j) continue;
                    chars[i] = (char)(j + 'a');
                }
            }
        }
        return new String(chars);
    }

    public String modifyString2(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for(int i = 0; i < n; i++) {
            if(chars[i] == '?') {
                if(i == 0) {
                    if(i < n - 1 && chars[i + 1] == '?') {
                        chars[i] = 'a';
                        continue;
                    }

                    if(i == n - 1) {
                        return "a";
                    }

                    int idx = chars[i + 1] - 'a';
                    idx = (idx + 1) % 26;
                    chars[i] = (char)(idx + 'a');
                } else if(i == n - 1 ) {
                    int idx = chars[i - 1] - 'a';
                    idx = (idx + 1) % 26;
                    chars[i] = (char)(idx + 'a');
                } else {
                    int prev = chars[i - 1] - 'a';
                    int next = chars[i + 1] == '?' ? 'a' : chars[i + 1] - 'a';
                    int idx = (prev + 1) % 26;
                    if(idx == next) {
                        idx = (idx + 1) % 26;
                    }
                    chars[i] = (char)(idx + 'a');
                }
            }

        }

        return new String(chars);
    }
}