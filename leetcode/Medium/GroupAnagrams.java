import java.util.*;

// 49 https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams obj = new GroupAnagrams();

        List<List<String>> lists = obj.groupAnagrams(new String[]{"bluejacket", "calibrates"});
        for (List<String> l : lists) {
            System.out.println(l);
        }

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return Collections.emptyList();
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            String key = Arrays.toString(count);
            if (!map.containsKey(key)) map.put(key, new LinkedList<>());
            map.get(key).add(s);
        }

        List<List<String>> results = new ArrayList<>();
        for (String s : map.keySet()) {
            results.add(map.get(s));
        }

        return results;
    }
}
