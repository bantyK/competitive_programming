import java.util.*;

// 981 https://leetcode.com/problems/time-based-key-value-store/
public class TimeMap {
    Map<String, List<Node>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        List<Node> nodes = map.get(key);
        Node newMode = new Node(value, timestamp);
        nodes.add(newMode);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<Node> nodes = map.get(key);
        int low = 0;
        int high = nodes.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (nodes.get(mid).timestamp > timestamp) {
                high = low - 1;
            } else {
                low = mid;
            }
        }
        if (nodes.get(low).timestamp > timestamp) return "";
        return nodes.get(low).value;
    }

    class Node {
        String value;
        int timestamp;

        public Node(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
