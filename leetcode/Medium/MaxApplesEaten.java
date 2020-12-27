import java.util.Comparator;
import java.util.PriorityQueue;

//1705 https://leetcode.com/problems/maximum-number-of-eaten-apples/submissions/
public class MaxApplesEaten {

    public static void main(String[] args) {
        MaxApplesEaten obj = new MaxApplesEaten();
        System.out.println(obj.eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}) == 7);
        System.out.println(obj.eatenApples(new int[]{3, 0, 0, 0, 0, 2}, new int[]{3, 0, 0, 0, 0, 2}) == 5);
        System.out.println(obj.eatenApples(new int[]{10, 10, 10, 10, 10}, new int[]{1, 1, 1, 1, 1}) == 5);
        System.out.println(obj.eatenApples(new int[]{1, 10, 3, 4, 5}, new int[]{1, 10, 1, 1, 1}));// == 11);
        System.out.println(obj.eatenApples(
                new int[]{9, 10, 1, 7, 0, 2, 1, 4, 1, 7, 0, 11, 0, 11, 0, 0, 9, 11, 11, 2, 0, 5, 5},
                new int[]{3, 19, 1, 14, 0, 4, 1, 8, 2, 7, 0, 13, 0, 13, 0, 0, 2, 2, 13, 1, 0, 3, 7}));// == 31);

    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int n = apples.length;
        int currentDay = 1;
        int count = 0;

        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[1] < currentDay) {
                pq.poll();
            }

            if (apples[i] == 0) {
                // no apple grew today
                if (!pq.isEmpty()) {
                    count++;
                    int[] pop = pq.poll();
                    if (pop[0] > 1) {
                        pq.offer(new int[]{pop[0] - 1, pop[1]});
                    }
                }
            } else {
                if (!pq.isEmpty()) {
                    // if there are apples at pq then we have 2 options,
                    // either eat an apple at index i, or eat the apple at the top of the queue
                    // to eat maximum apples at the end, we will eat the apple which is gotten rotten earlier
                    if (pq.peek()[1] > i + days[i]) {
                        count++;
                        // the current apple is getting rotten earlier
                        if (apples[i] > 1) {
                            pq.offer(new int[]{apples[i] - 1, i + days[i]});
                        }
                    } else {
                        count++;
                        // the apple at the peek is getting rotten before the apple at current index
                        int[] pop = pq.poll();
                        // eat the apple at the top
                        if (pop[0] > 1) {
                            pq.offer(new int[]{pop[0] - 1, pop[1]});
                        }
                        // also add the apple and days at index i into the stack
                        // because I have not eaten the apple at the current index, we will add the apple count as is
                        pq.offer(new int[]{apples[i], i + days[i]});
                    }
                } else {
                    // there are no apples at the pq
                    // eat the current apple
                    count++;
                    if (apples[i] > 1) {
                        pq.offer(new int[]{apples[i] - 1, i + days[i]});
                    }
                }
            }

            currentDay++;
        }

        // while there are apple remaining to eat
        while (!pq.isEmpty()) {
            int[] pop = pq.poll();
            // if this apple rotten day is less than the current day, we will not eat this apple
            if (pop[1] < currentDay) continue;
            // this apple has not rotten, we will eat this apple and decrement its count in the pq
            count++;
            if (pop[0] > 1) {
                pq.offer(new int[]{pop[0] - 1, pop[1]});
            }

            currentDay++;
        }

        return count;
    }
}
