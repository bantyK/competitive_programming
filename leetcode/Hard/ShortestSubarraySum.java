import java.util.*;

// 863 https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
// Read this before solving it: 
// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
public class ShortestSubarraySum {
    public static void main(String[] args) {
        ShortestSubarraySum obj = new ShortestSubarraySum();
        System.out.println(obj.shortestSubarray(new int[] { 2, -1, 2, 1 }, 3));
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n + 1];

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }

        // The deque will store indices. These are the possible values of start pointer
        // The prefix sum of those indices will be in increasing order
        // but presum[i3] < presum[i1] < presum[i2]
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n + 1; i++) {
            // we are checking the indices in the increasing order
            // and the presum are also sorted in increasing order in the deque, largest
            // first
            //
            while (!deque.isEmpty() && presum[i] - presum[deque.peekFirst()] >= k) {
                // all the indices which satisfy the condition P[end] - P[start] >= k
                // this is the same step that we do in normal sliding window problem with all
                // positive number (Problem #209)
                // we keep increasing the start untill our condition is satisfied, because it
                // will keep giving us better result
                ans = Math.min(ans, i - deque.pollFirst());
            }

            // this part of the code will keep the deque sorted.
            while (!deque.isEmpty() && presum[deque.peekLast()] >= presum[i]) {
                // this basically maintains that the presum are in increasing order
                // if the last element of the dq is greater than current element, remove the
                // last element
                // this will maintain that the queue will contain presums in increasing order

                // Another reason we are removing the last index is because, the presum in the
                // current index is less than
                // the presum as peekLast index. so if the condition is satified at
                // presum[peekLast] then the condition
                // will also be satisified for presum[i], and i is a later index than peekLast,
                // hence this will definetely
                // give smaller length of subarray
                deque.pollLast();
            }

            deque.offerLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}