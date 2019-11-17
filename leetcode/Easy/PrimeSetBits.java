package solutions.easy;

import java.util.*;

//762 https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
public class PrimeSetBits {
    public static void main(String[] args) {
        PrimeSetBits obj = new PrimeSetBits();
		int res = obj.countPrimeSetBits(10, 15);
		System.out.println(res);
    }

	public int countPrimeSetBits(int L, int R) {
    	int bitsCount = 0;
		int result = 0;
		
		for(int i = L; i <= R; i++) {
			bitsCount = Integer.bitCount(i);
			if(isPrime(bitsCount)) {
				result += 1;
			}
		}

		return result;
    }

	private boolean isPrime(int n) {
	    // Corner cases
    	if (n <= 1) return false;
    	if (n <= 3) return true;

    	if (n % 2 == 0 || n % 3 == 0) return false;

    	for (int i=5; i*i<=n; i=i+6)
        	if (n % i == 0 || n % (i+2) == 0)
        		return false;
    	return true;
	}
}
