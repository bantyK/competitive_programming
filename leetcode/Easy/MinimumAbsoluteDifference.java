// https://leetcode.com/problems/minimum-absolute-difference/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference {

    public static void main(String[] args) {
        MinimumAbsoluteDifference obj = new MinimumAbsoluteDifference();
        List<List<Integer>> lists = obj.minimumAbsDifference(new int[] { 188,9,-189,-112,165,4,-141,179,-154,258,53,71,201,204,121,215,259,-22,34,-213,-88,-192,118,-221,130,-86,209 });
        for (List<Integer> list : lists) {
            System.out.println("[" + list.get(0) + ", " + list.get(1) + "]");
        }
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
        int minimumDifference = arr[1] - arr[0];
		int currentDifference;
		
		for(int i = 0; i < arr.length - 1; i++) {
			currentDifference = arr[i+1] - arr[i];
			if(currentDifference < minimumDifference) {
				minimumDifference = currentDifference;
			}
			System.out.println(minimumDifference);
		}
		
        int second;
        int first;

        for (int i = 0; i < arr.length - 1; i++) {
            second = arr[i + 1];
            first = arr[i];
            if (second - first == minimumDifference) {
                result.add(Arrays.asList(first, second));
            }
        }

        return result;
    }

}
