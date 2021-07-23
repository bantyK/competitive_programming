import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//1424 https://leetcode.com/problems/diagonal-traverse-ii/
public class DiagonalTraversalII {

    public static void main(String[] args) throws IOException {
        DiagonalTraversalII obj = new DiagonalTraversalII();
        List<List<Integer>> nums = TwoDArrayReader.get2DList();
        System.out.println(Arrays.toString(obj.findDiagonalOrder(nums)));
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.size();
        int count = 0;
        int maxKey = 0;
        for(int r = n - 1; r >= 0; r--) {
            for(int c = 0; c < nums.get(r).size(); c++) {
                map.putIfAbsent(r + c, new ArrayList<>());
                Integer e = nums.get(r).get(c);
                map.get(r + c).add(e);
                ++count;
                maxKey = Math.max(maxKey, r + c);
            }
        }

        int[] res = new int[count];
        int i = 0;
        for(int key = 0; key <= maxKey; key++) {
            List<Integer> values = map.get(key);
            if(values == null || values.size() == 0) continue;
            for(int val : values)
                res[i++] = val;
        }

        return res;
    }

}