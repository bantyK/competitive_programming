import java.util.*;
// 703 https://leetcode.com/problems/kth-largest-element-in-a-stream/
class KthLargest {
    PriorityQueue<Integer> heap = new PriorityQueue<Integer>((a,b) -> a - b);
    private int K;
    public KthLargest(int k, int[] nums) {
        this.K = k;
        for(int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        if(heap.size() < K) {
            heap.add(val);
        } else {
            if(val > heap.peek()) {
                heap.poll();
                heap.add(val);
            }
        }
        return heap.peek();
    }
}
