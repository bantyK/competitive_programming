import java.util.PriorityQueue;

//1642 https://leetcode.com/problems/furthest-building-you-can-reach/
public class FurthestBuilding {

    /**
     * Look into past where we have used bricks to see if it make sense to use a ladder there
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;

        for (; i < heights.length - 1; i++) {
            if (heights[i + 1] < heights[i]) {
                // next building is lower than current building, no need to use brick or ladder
                // simply continue
                continue;
            }

            int diff = heights[i + 1] - heights[i];
            if (diff <= bricks) {
                // we have enough bricks, use them for now.
                // will revisit if we should have used a ladder here instead
                bricks -= diff;
                pq.offer(diff); // adding this in PQ will help us revisit this later
            } else if (ladders > 0) {
                if (!pq.isEmpty()) {
                    int maxBricksUsedInPast = pq.peek();
                    if (maxBricksUsedInPast > diff) {
                        // we have used some bricks in the past, which is greater than the number of
                        // bricks that we need at this position.
                        // If we use a ladder in past and use bricks here, we will save some bricks here
                        // which can be used later
                        bricks += pq.poll(); // get the bricks from past, we will use a ladder there
                        bricks -= diff; // use some bricks here, even after using bricks here, we will save some
                        pq.offer(diff); // put it in PQ to use the same logic again in future
                    }
                }
                // count of ladders are going to decrease, whether we use the bricks here and aaa ladder at the past,
                // or directly use a ladder here
                ladders--;
            } else {
                break;
            }
        }

        return i;
    }
}