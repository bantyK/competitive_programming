import java.util.*;

//911 https://leetcode.com/problems/online-election/
class TopVotedCandidate {
    int[] persons;
    int[] times;

    Map<Integer, Integer> countMap;
    Map<Integer, Integer> leadMap;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        countMap = new HashMap<>();
        leadMap = new HashMap<>();
        int lead = -1;
        // find the leads in the different times beforehand.
        // At the time of query, we will find the time from times and find the lead during that time.
        for (int i = 0; i < persons.length; i++) {
            countMap.put(persons[i], countMap.getOrDefault(persons[i], 0) + 1);
            if (i == 0 || countMap.get(persons[i]) >= countMap.get(lead)) {
                lead = persons[i];
            }
            leadMap.put(times[i], lead);
        }
    }

    public int q(int t) {
        int idx = binarySearch(times, t);
        return leadMap.get(times[idx]);
    }

    private int binarySearch(int[] nums, int num) {
        // returns the index of element in nums less than or equal to num
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (nums[mid] > num) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }

        return low;
    }
}
