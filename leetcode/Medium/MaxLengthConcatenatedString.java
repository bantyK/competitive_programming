import java.util.*;
//1239 https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
public class MaxLengthConcatenatedString {

    public static void main(String[] args) {
        List<String> arr = Arrays.asList("cha", "r", "act", "ers");
        MaxLengthConcatenatedString obj = new MaxLengthConcatenatedString();
        obj.maxLength(arr);
        System.out.println(obj.maxLen);
    }

    int maxLen;

    public int maxLength(List<String> arr) {
        maxLen = 0;
        List<String> unique = new ArrayList<>();
        for(String s : arr) {
            if (!containsDuplicateChars(s)) {
                unique.add(s);
            }
        }
        helper(new StringBuilder(), unique, 0);
        return maxLen;
    }

    private void helper(StringBuilder builder, List<String> list, int index) {
        if (index >= list.size()) {
            return;
        }

        int lastLen;
        for(int i = index; i < list.size(); i++) {
            if(!builderContainsDuplicates(builder.toString(), list.get(i))) {
                lastLen = list.get(i).length();
                builder.append(list.get(i));
                maxLen = Math.max(maxLen, builder.length());

                helper(builder, list, i + 1);

                builder.setLength(builder.length() - lastLen);
            }
        }
    }

    private boolean builderContainsDuplicates(String str, String newString) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i <str.length(); i++) {
            set.add(str.charAt(i));
        }

        for(int i = 0; i < newString.length(); i++) {
            if(set.contains(newString.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDuplicateChars(String s) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            if(!set.add(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}