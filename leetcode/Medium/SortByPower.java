import java.util.*;

//1387 https://leetcode.com/problems/sort-integers-by-the-power-value/
public class SortByPower {
    public static void main(String[] args) {
        SortByPower obj = new SortByPower();
        System.out.println(obj.getKth(12, 15, 2));
        System.out.println(obj.getKth(1, 1000, 777));
    }

    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> powerGraph = new HashMap<>();
        powerGraph.put(1, 0);
        List<Integer> values = new ArrayList<>();

        for (int i = lo; i <= hi; i++) {
            values.add(i);
            getPower(powerGraph, i);
        }

// Using custom search method
//        values.sort((i, j) -> {
//            final int powerI = powerGraph.get(i);
//            final int powerJ = powerGraph.get(j);
//            if (powerI == powerJ) return i - j;
//            else return powerI - powerJ;
//        });
//
//        return values.get(k - 1);


        // Using max heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((i, j) -> {
            final int powerI = powerGraph.get(i);
            final int powerJ = powerGraph.get(j);
            if (powerI == powerJ) return j - i;
            else return powerJ - powerI;
        });

        for (int val : values) {
            heap.add(val);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();
    }

    public int getPower(Map<Integer, Integer> powerGraph, int val) {
        if (val == 1) {
            return 0;
        }
        if (powerGraph.containsKey(val)) return powerGraph.get(val);

        int power;
        if (val % 2 == 0) {
            power = getPower(powerGraph, val / 2) + 1;
        } else {
            power = getPower(powerGraph, val * 3 + 1) + 1;
        }
        powerGraph.put(val, power);
        return power;
    }
}
