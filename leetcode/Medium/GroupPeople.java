import java.util.*;

//1282 https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
public class GroupPeople {
    public static void main(String[] args) {
        GroupPeople obj = new GroupPeople();
        int[] groupSizes = new int[]{2, 1, 3, 3, 3, 2};
        List<List<Integer>> result = obj.groupThePeople(groupSizes);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        if (groupSizes == null || groupSizes.length == 0) return new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int groupSize = groupSizes[i];
            if (!map.containsKey(groupSize)) {
                map.put(groupSize, new ArrayList<>());
            }

            List<Integer> values = map.get(groupSize);
            if (values.size() < groupSize) {
                values.add(i);
            } else {
                result.add(values);
                map.put(groupSize, new ArrayList<>());
                map.get(groupSize).add(i);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key).size() > 0) {
                result.add(map.get(key));
            }
        }

        return result;
    }
}
