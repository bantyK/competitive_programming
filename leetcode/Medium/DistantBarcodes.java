import java.util.*;

// 1054 https://leetcode.com/problems/distant-barcodes/
public class DistantBarcodes {
    public static void main(String[] args) {
        DistantBarcodes obj = new DistantBarcodes();
        System.out.println(Arrays.toString(obj.rearrangeBarcodes(new int[]{1, 1, 1, 2, 2, 2})));
        System.out.println(Arrays.toString(obj.rearrangeBarcodes(new int[]{1, 1, 1, 1, 2, 2, 3, 3})));
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int code : barcodes) {
            countMap.put(code, countMap.getOrDefault(code, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));

        maxHeap.addAll(countMap.keySet());

        int[] res = new int[barcodes.length];
        int resultIndex = 0;
        List<Integer> tempList;

        while(maxHeap.size() >= 2) {
            int num1 = maxHeap.poll();
            int num2 = maxHeap.poll();
            res[resultIndex++] = num1;
            res[resultIndex++] = num2;

            if(countMap.get(num1) - 1 > 0) {
                countMap.put(num1, countMap.get(num1) - 1);
                maxHeap.add(num1);
            }
            if(countMap.get(num2) - 1 > 0) {
                countMap.put(num2, countMap.get(num2) - 1);
                maxHeap.add(num2);
            }
        }

        if(!maxHeap.isEmpty()) {
            res[resultIndex] = maxHeap.poll();
        }

        return res;
    }

}
