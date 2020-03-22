import java.util.*;

//1087 https://leetcode.com/problems/brace-expansion/
public class BraceExpansion {
    public static void main(String[] args) {
        BraceExpansion obj = new BraceExpansion();

        System.out.println(Arrays.toString(obj.expand("{a,b}c{d,ex}")));
    }

    public String[] expand(String s) {
        List<String> buckets = new ArrayList<>();

        final int len = s.length();
        for (int i = 0; i < len; i++) {
            StringBuilder builder = new StringBuilder();
            if (s.charAt(i) == '{') {
                int j = i + 1;
                while (j < len && s.charAt(j) != '}') {
                    if (s.charAt(j) == ',') {
                        j++;
                        continue;
                    }
                    builder.append(s.charAt(j));
                    j++;
                }
                buckets.add(builder.toString());
                i = j;
            } else {
                buckets.add(s.charAt(i) + "");
            }


        }
        List<String> result = new ArrayList<>();
        dfs(buckets, result, new StringBuilder(), 0);

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private void dfs(List<String> buckets, List<String> result, StringBuilder builder, int index) {
        if (builder.length() == buckets.size()) {
            result.add(builder.toString());
            return;
        }

        for (char c : buckets.get(index).toCharArray()) {
            builder.append(c);
            dfs(buckets, result, builder, index + 1);
            builder.setLength(builder.length() - 1);
        }
    }


}
