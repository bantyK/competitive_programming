package algorithms;

public class PairsDivisibleBy60 {

	public static void main(String[] args) {
		PairsDivisibleBy60 obj = new PairsDivisibleBy60();
		System.out.println(obj.numPairsDivisibleBy60(new int[]{30,20,150,100,40}));
	}
	
	public int numPairsDivisibleBy60(int[] time) {
		int[] remainder = new int[60];
		
		for(int t : time) {
			remainder[t % 60]++;
		}
		
		// numbers whose modules with 60 gives 0 and 30, can make numbers divisible by 60 among themselves. 
		int count = remainder[0] * (remainder[0] - 1) / 2;
		count += remainder[30] * (remainder[30] - 1) / 2;
		
		for(int i = 1; i < 30; i++) {
			count += remainder[i] * remainder[60 - i];
		}
		
		return count;
	}

}
