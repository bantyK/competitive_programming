import java.util.*;

// 833 https://leetcode.com/problems/find-and-replace-in-string/
public class FindReplaceString {
    public static void main(String[] args) {
        FindReplaceString obj = new FindReplaceString();

        System.out.println(
                obj.findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"})
        );

        System.out.println(obj.findReplaceString("aa", new int[]{0, 1}, new String[]{"a", "a"}, new String[]{"eee", "ffff"}));


    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, String[]> map = new HashMap<>();
        for (int i = 0; i < sources.length; i++) {
            map.put(indexes[i], new String[]{sources[i], targets[i]});
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(i)) {
                String source = map.get(i)[0];
                int len = source.length();
                if (i + len <= S.length() && S.substring(i, i + len).equals(source)) {
                    builder.append(map.get(i)[1]);
                    i += len - 1;
                } else {
                    builder.append(S.charAt(i));
                }
            } else {
                builder.append(S.charAt(i));
            }
        }

        return builder.toString();
    }
}
