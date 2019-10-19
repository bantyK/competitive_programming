package solutions.medium;

import java.util.*;

public class KFrequentElements {

    public static void main(String[] args) {
        KFrequentElements obj = new KFrequentElements();
        List<Integer> list = obj.topKFrequent(new int[]{2, 2, 3, 1, 1, 1,1, 1, 1,1, 1, 1,1, 1, 1, 3, 3, 3, 3, 2, 2, 2}, 2);

        for (int e : list) {
            System.out.print(e + " ");
        }
    }


    // Quick select approach

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.values());

        int min = quickSelect(list, k - 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= min) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    private int quickSelect(List<Integer> list, int k) {
        int l = 0, r = list.size() - 1;
        while (true) {
            int pivot = list.get(r);
            int i = l;
            for (int j = l; j < r; j++) {
                if (list.get(j) > pivot)
                    swap(i++, j, list);
            }
            swap(r, i, list);

            if (i > k) {
                r = i - 1;
            } else if (i < k){
                l = i + 1;
            } else {
                return pivot;
            }
        }
    }

    private int quickSelectHelper(List<Integer> list, int start, int end, int k) {
        if (start <= end) {

            final int partitionIndex = partition(list, start, end);

            if (partitionIndex == k) {
                return list.get(k);
            } else if (partitionIndex > k) {
                quickSelectHelper(list, start, partitionIndex - 1, k);
            } else {
                quickSelectHelper(list, partitionIndex + 1, end, k);
            }
        }

        return -1;
    }

    private int partition(List<Integer> list, int start, int end) {
        int partitionIndex = start;
        int pivot = list.get(end);

        for (int i = start; i < end; i++) {
            if (list.get(i) <= pivot) {
                swap(partitionIndex, i, list);
                partitionIndex++;
            }
        }

        swap(partitionIndex, end, list);

        return partitionIndex;
    }

    private void swap(int i, int j, List<Integer> l) {
        int tmp = l.get(i);
        l.set(i, l.get(j));
        l.set(j, tmp);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Using a priority queue
    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((num1, num2) -> getCount(nums, num2) - getCount(nums, num1));
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                set.add(num);
                pq.add(num);
            }
        }

        for (int i = 0; i < k; i++) {
            int count = pq.remove();
            result.add(count);
        }
        return result;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Naive approach, brute force
    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!countMap.containsKey(nums[i])) {
                countMap.put(nums[i], getCount(nums, nums[i]));
            }
        }
        Map<Integer, Integer> sortedMap = sortMapByValue(countMap);


        for (int key : sortedMap.keySet()) {
            result.add(key);
            k--;
            if (k <= 0) {
                break;
            }
        }

        return result;
    }

    private Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());

        HashMap<Integer, Integer> tempMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            tempMap.put(entry.getKey(), entry.getValue());
        }

        return tempMap;
    }

    private int getCount(int[] nums, int n) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n) {
                count += 1;
            }
        }
        return count;
    }
}
