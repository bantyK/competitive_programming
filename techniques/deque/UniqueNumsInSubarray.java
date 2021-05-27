import java.util.Deque;
import java.util.LinkedList;

// https://www.hackerrank.com/challenges/java-dequeue/problem

public class UniqueNumsInSubarray {
    private int countUniqueNumsInSubarrays(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();

        int maxUniqueCount = 0;

        for (int i = 0; i < nums.length; i++) {
            // if deque's left entry is outside the window then pop it out
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] == nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            if (i >= k - 1) {
                maxUniqueCount = Math.max(maxUniqueCount, deque.size());
            }
        }

        return maxUniqueCount;
    }

}