package solutions.easy;

import java.util.*;

// 1046 https://leetcode.com/problems/last-stone-weight/
public class StoneWeight {
    public static void main(String[] args) {
        StoneWeight obj = new StoneWeight();

        System.out.println(
                obj.lastStoneWeight(new int[]{1, 1, 1, 1})
        );
    }

    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1) return stones[0];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            int remain = Math.abs(stone1 - stone2);

            maxHeap.add(remain);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
