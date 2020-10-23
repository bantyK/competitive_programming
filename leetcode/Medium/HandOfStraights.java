import java.util.*;

//846 https://leetcode.com/problems/hand-of-straights/
public class HandOfStraights {
    public static void main(String[] args) {
        HandOfStraights obj = new HandOfStraights();
    }

    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : hand) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(countMap.keySet());

        while (!minHeap.isEmpty()) {
            // temp tracks the number that we have polled out of head, if that number occurs more than once and
            // we have not exhausted all occurrences of that number than we need to put that number back to our heap.
            List<Integer> temp = new ArrayList<>();
            int first = minHeap.poll();
            temp.add(first);
            for (int i = 0; i < W - 1; i++) { // W - 1 because one card is already polled in the previous line
                if (minHeap.isEmpty()) {
                    // no card to poll, not a valid hand
                    return false;
                }
                int second = minHeap.poll();
                if (second - first != 1) {
                    // the cards are not consecutive. Not a valid hand
                    return false;
                }

                temp.add(second);
                first = second; // second becomes first for the next iteration because the next card should be consecutive from second card of this instance
            }

            for (int num : temp) {
                // all the occurance of this num is not used if the count - 1 > 0, hence we need to decrement its count by 1 because it has been used once
                // and add it back to heap for getting used in the next iterations.
                if (countMap.get(num) - 1 > 0) {
                    minHeap.add(num);
                    countMap.put(num, countMap.get(num) - 1);
                }
            }
        }

        return true;
    }
}
