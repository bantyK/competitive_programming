package segmenttree;

import java.util.*;

// Construction, implementation and update operation in a segment tree
// Array implementation
public class SegmentTreeImpl {

    public static void main(String[] args) {
        SegmentTreeImpl obj = new SegmentTreeImpl();
        Map<Integer, Integer> segmentMap = obj.constructST(new int[]{1, 2, 5, 6, 7, 9});
        System.out.println(segmentMap);
    }

    public Map<Integer, Integer> constructST(int[] arr) {
        Map<Integer, Integer> segmentTreeList = new HashMap<>();

        constructST(arr, segmentTreeList, 0, arr.length - 1, 0);

        return segmentTreeList;
    }

    private int constructST(int[] arr, Map<Integer, Integer> segmentTreeMap, int left, int right, int position) {
        if (left == right) {
            // leaf node
            segmentTreeMap.put(position, arr[left]);
            return arr[left];
        }

        int mid = left + (right - left) / 2;

        int leftVal = constructST(arr, segmentTreeMap, left, mid, 2 * position + 1);
        int rightVal = constructST(arr, segmentTreeMap, mid + 1, right, 2 * position + 2);
        segmentTreeMap.put(position, leftVal + rightVal);
        return leftVal + rightVal;
    }
}