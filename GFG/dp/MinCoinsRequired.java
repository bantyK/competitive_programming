/*package whatever //do not write package name here */
//https://practice.geeksforgeeks.org/problems/number-of-coins/0/

import java.util.*;

class GFG {
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		s.nextLine();
		
		for(int k = 0; k < t; k++) {
		    String[] lines = s.nextLine().split(" ");
		    
		    int value = Integer.parseInt(lines[0]);
		    int N = Integer.parseInt(lines[1]);
		    
		    int[] coins = new int[N];
		    
		    lines = s.nextLine().split(" ");
		    for(int i = 0; i < N; i++) {
		        coins[i] = Integer.parseInt(lines[i]);
		    }
		    
		    int count = minCoins(coins, value);
		    if(count != Integer.MAX_VALUE) {
		        System.out.println(count);
		    } else {
		        System.out.println(-1);
		    }
		}
	}
	
	private static int minCoins(int[] coins, int value) {
	    Integer[][] dp = new Integer[coins.length][value + 1];
	    return helper(coins, value, 0, dp); 
	}
	
	
	private static int helper(int[] coins, int value, int index, Integer[][] dp) {
	    if(index >= coins.length) {
	        return Integer.MAX_VALUE;
	    }
	    
	    if(value == 0) {
	        return 0;
	    }
	    
	    if(dp[index][value] != null) {
	        return dp[index][value];
	    }
	    
	    int count1 = Integer.MAX_VALUE;
	    
	    if(coins[index] <= value) {
	       int res = helper(coins, value - coins[index], index, dp);
	       if(res != Integer.MAX_VALUE) {
	           count1 = res + 1;
	       }
	    }
	   
	   int count2 = helper(coins, value, index + 1, dp);
	   
	   dp[index][value] = Math.min(count1, count2);
	   return dp[index][value];
	}
	
}
