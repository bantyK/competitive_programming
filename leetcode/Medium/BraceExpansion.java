import java.util.*;

//1087 https://leetcode.com/problems/brace-expansion/
public class BraceExpansion {
    public static void main(String[] args) {
        BraceExpansion obj = new BraceExpansion();

        System.out.println(Arrays.toString(obj.expand("{a,b}c{d,e}f")));
    }


    private List<String> res;

    public String[] expand(String s) {
        res = new ArrayList<>();
        dfs(s, 0, new StringBuilder());
        String[] out = new String[res.size()];
        int i = 0;
        for (String ss : res) {
            out[i++] = ss;
        }
        return out;
    }

    private void dfs(String s, int index, StringBuilder builder) {
        if (index == s.length()) {
            res.add(builder.toString());
            return;
        }

        char ch = s.charAt(index);

        if (ch == '{') {
            List<Character> letters = new ArrayList<>();
            int endIndex = index + 1;
            while (endIndex < s.length() && s.charAt(endIndex) != '}') {
                if (Character.isLetter(s.charAt(endIndex))) {
                    letters.add(s.charAt(endIndex));
                }
                endIndex++;
            }

            Collections.sort(letters);
            int prevLen = builder.length();

            for (char letter : letters) {
                builder.append(letter);
                // recurse to the next bucket in the string
                dfs(s, endIndex + 1, builder);
                // delete all the chars added to the builder before making this recursive call
                builder.setLength(prevLen);
            }
        } else if (Character.isLetter(ch)) {
            builder.append(ch);
            dfs(s, index + 1, builder);
        }
    }

    public List<String> expand2(String s) {
        List<String> res = new ArrayList<>();
        List<List<String>> buckets = new ArrayList<>();

        generateWords(buckets, 0, 0, res, new LinkedList<String>());

        System.out.println(res);
        return res;
    }

    private void generateWords(List<List<String>> buckets, int bucketIndex, int index, List<String> res, LinkedList<String> builder) {
        if (bucketIndex == buckets.size()) {
            res.add(builder.toString());
            return;
        }

        for (int i = bucketIndex; i < buckets.size(); i++) {
            while (index < buckets.get(bucketIndex).size()) {
                builder.add(buckets.get(bucketIndex).get(index));
                generateWords(buckets, bucketIndex + 1, 0, res, builder);
                index++;
                builder.removeLast();
            }
//            builder.removeLast();
        }
    }


}
