import java.util.*;

public class StringPermutation {
    public static void main(String[] args) {
        StringPermutation obj = new StringPermutation();
        HashSet<Integer> set = new HashSet<>();
        obj.permute("ab", new StringBuilder(), set);
    }

    private void permute(String s, StringBuilder builder, HashSet<Integer> visited) {
        if (builder.length() == s.length()) {
            System.out.println(builder.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if(visited.contains(i)) continue;

            visited.add(i);
            builder.append(s.charAt(i));
            permute(s, builder, visited);
            builder.deleteCharAt(builder.length() - 1);
            visited.remove(i);
        }
    }
}
