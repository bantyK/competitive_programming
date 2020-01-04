// https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps/0
class GFG {
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		s.nextLine();
		
		for(int k = 0; k < t; k++) {
		    int n = s.nextInt();
		    s.nextLine();
		    int[] jumps = new int[n];
		    String[] line = s.nextLine().split(" ");
		    for(int i = 0; i < n; i++) {
		        jumps[i] = Integer.parseInt(line[i]);
		    }
		    Integer[] dp = new Integer[n];
		    int minJumps = minJumps(jumps, 0, dp);
		    if(minJumps == Integer.MAX_VALUE) {
		        minJumps = -1;
		    }
		    System.out.println(minJumps);
		}
	}
	
	private static int minJumps(int[] jumps, int currentIndex, Integer[] dp) {
	    if(currentIndex == jumps.length - 1) {
	        return 0;
	    }
	    
	    if(jumps[currentIndex] == 0) {
	        return Integer.MAX_VALUE;
	    }
	    
	    if(dp[currentIndex] != null) {
	        return dp[currentIndex];
	    }
	    
	    int totalJumps = Integer.MAX_VALUE;
	    int start = currentIndex + 1;
	    int end = currentIndex + jumps[currentIndex];
	    
	    while(start < jumps.length && start <= end) {
	        int minJumps = minJumps(jumps, start, dp);
	        start+=1;
	        if(minJumps != Integer.MAX_VALUE) {
	            totalJumps = Math.min(totalJumps, minJumps + 1);
	        }
	    }
	    
	    dp[currentIndex] = totalJumps;
	    return totalJumps;
	}
}
