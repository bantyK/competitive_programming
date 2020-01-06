import java.util.*;
// 380 https://leetcode.com/problems/insert-delete-getrandom-o1/

class RandomizedSet {
    Map<Integer, Integer> map; // valie-index map
    List<Integer> nums;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val); // index of the element which is to be removed
            int lastIndex = nums.size() - 1; // last index
            int lastItem = nums.get(lastIndex);
            nums.set(index, nums.get(lastIndex));
            map.put(lastItem, index);
            nums.remove(lastIndex);
            map.remove(val);
            return true;
        }

        return false;
    }

    public int getRandom() {
        int i = random.nextInt(nums.size());
        return nums.get(i);
    }
}