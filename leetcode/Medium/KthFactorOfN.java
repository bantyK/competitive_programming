//1492 https://leetcode.com/problems/the-kth-factor-of-n/
public class KthFactorOfN {
    public int kthFactor(int n, int k) {
        for(int i = 1; i <= n; i++) {
        	if(n % i == 0) {
        		if(k == 1) return i;
        		else k--;
        	}
        }
        return -1;
    }
}
