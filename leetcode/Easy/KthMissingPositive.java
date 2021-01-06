// 1539 https://leetcode.com/problems/kth-missing-positive-number/
public class KthMissingPositive {
    public int findKthPositive(int[] arr, int k) {
        int i = 0;
        int actualNum = 1;
        while (i < arr.length) {
            if (arr[i] == actualNum) {
                i++;
            } else {
                k--;

                if (k == 0) {
                    return actualNum;
                }
            }
            actualNum++;
        }
        return arr[arr.length - 1] + k;
    }

}