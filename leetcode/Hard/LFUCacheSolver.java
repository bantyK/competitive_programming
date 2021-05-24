import java.util.*;

// 460 https://leetcode.com/problems/lfu-cache/
public class LFUCacheSolver {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(5);
        cache.put(1, 1);
    }

    static class LFUCache {

        final int capacity;
        int minFrequency;
        Map<Integer, Integer> countMap;
        Map<Integer, LinkedHashSet<Integer>> frequencyMap;
        Map<Integer, Integer> valueMap;


        public LFUCache(int capacity) {
            this.capacity = capacity;
            countMap = new HashMap<>();
            frequencyMap = new HashMap<>();
            valueMap = new HashMap<>();
            minFrequency = -1;

            frequencyMap.put(1, new LinkedHashSet<>());
        }

        public int get(int key) {
            if (!valueMap.containsKey(key)) return -1;

            // the key is accessed, so we have to increase its frequency
            int prevCount = countMap.get(key);
            countMap.put(key, prevCount + 1);

            // remove the key from its current frquency list
            frequencyMap.get(prevCount).remove(key);

            // increase the minFrequency if the accessed key is the last item of that frequency
            if (prevCount == minFrequency && frequencyMap.get(minFrequency).size() == 0) {
                ++minFrequency;
            }

            // add this key in the frequencyMap in the list against its new Count
            frequencyMap.putIfAbsent(prevCount + 1, new LinkedHashSet<Integer>());
            frequencyMap.get(prevCount + 1).add(key);

            return valueMap.get(key);
        }

        public void put(int key, int value) {
            if (this.capacity <= 0) return;

            if (valueMap.containsKey(key)) {
                // the already exist, we just need to update the value of the key with new value and
                // increase its frequency (in case the value is updated)
                valueMap.put(key, value);
                get(key); // this will increase the frequency of the key
                return;
            }

            // key does not exist
            if (valueMap.size() == capacity) {
                // the cache is already full, least frequent key has to be deleted

                // this is the head(least recently used) of the least frequent DLL
                int leastFrequentKey = frequencyMap.get(minFrequency).iterator().next();
                // remove this key
                frequencyMap.get(minFrequency).remove(leastFrequentKey);
                // remove it from value map also
                valueMap.remove(leastFrequentKey);
            }

            // add it in the cache
            valueMap.put(key, value);
            // this is a new key, otherwise it would have returned from above, hence its count = 1
            countMap.put(key, 1);
            // add it the list of frequency map also
            frequencyMap.get(1).add(key);
            minFrequency = 1; // since this is a new key just added, the minimum frequency will be 1
        }
    }
}