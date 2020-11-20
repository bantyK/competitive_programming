import java.util.*;

//1146 https://leetcode.com/problems/snapshot-array/
class SnapshotArray {
    // Solution using TreeMap
    class SnapshotArrayTreeMap {

        TreeMap<Integer, Integer>[] map;
        int snapId;

        public SnapshotArrayTreeMap(int length) {
            snapId = 0;
            map = new TreeMap[length];

            for (int i = 0; i < length; i++) {
                map[i] = new TreeMap<>();
                map[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            map[index].put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            // find smallest index greater than or equal to snap_id and return that value
            int key = map[index].floorKey(snap_id);
            return map[index].get(key);
        }
    }

    // Solution using TreeMap
    class SnapshotArrayHashMap {
        List<Map<Integer, Integer>> shots;
        Map<Integer, Integer> diff;

        public SnapshotArrayHashMap(int length) {
            shots = new ArrayList<>();
            diff = new HashMap<>();
        }

        public void set(int index, int val) {
            diff.put(index, val);
        }

        public int snap() {
            shots.add(diff);
            diff = new HashMap<>();
            return shots.size() - 1;
        }

        public int get(int index, int snap_id) {
            for (int i = snap_id; i >= 0; i--) {
                if (shots.get(i).containsKey(index)) {
                    return shots.get(i).get(index);
                }
            }
            return 0;
        }
    }
}