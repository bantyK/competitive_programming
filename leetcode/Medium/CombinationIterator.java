import java.util.*;

// 5123 https://leetcode.com/problems/iterator-for-combination/
class CombinationIterator {
    private String s;
    private int size;
    private Queue<String> permutations = new ArrayDeque<>();

    public CombinationIterator(String characters, int combinationLength) {
        this.s = characters;
        this.size = combinationLength;
        permuteString(s, permutations, new StringBuilder(), size, 0);
    }

    public String next() {
        return permutations.poll();
    }

    public boolean hasNext() {
        return !permutations.isEmpty();
    }


    private void permuteString(String s, Queue<String> result, StringBuilder builder, int size, int index) {
        if (builder.length() == size) {
            result.offer(builder.toString());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            builder.append(s.charAt(i));
            permuteString(s, result, builder, size, i + 1);
            builder.deleteCharAt(builder.length() - 1);
        }

    }
}
