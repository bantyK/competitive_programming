// 1551 https://leetcode.com/problems/minimum-operations-to-make-array-equal/
public class MinOperationToMakeArrayEqual {
	public int minOperations(int n) {
        int[] arr = new int[n];
        int sum = 0;
        
        for(int i = 0; i < n; i++) {
            arr[i] = 2 * i + 1;
            sum += arr[i];
        }
        
        int target = sum / n;
        
        int left = 0;
        int right = n/2;
        
        int minSteps = 0;
        
        for(int i = 0; i < (n/2); i++) {
            minSteps += (target - arr[i]);
        }
        
        return minSteps;
    }

    // Without constructing the actual array

	public int minOperations2(int n) {
        int sum = 0;
        
        for(int i = 0; i < n; i++) {
            sum += 2 * i + 1;
        }
        
        int target = sum / n;
        
        int left = 0;
        int right = n/2;
        
        int minSteps = 0;
        
        for(int i = 0; i < (n/2); i++) {
            minSteps += (target - (2 * i + 1));
        }
        
        return minSteps;
    }
}
