// 62 https://leetcode.com/problems/unique-paths/submissions/

public class UniquePaths1 {
	public int uniquePaths(int m, int n) {
        if(m == 0) return 0;
        if(m == 1 && n == 1) return 1;
        int rows = m;
        int cols = n;
    	// dp[i][j] represent the number of ways to reach from starting point to the point represented by coordinated(i,j) 
		  
        int[][] dp = new int[rows][cols];
        dp[0][0] = 0;
        
        for(int j = 1; j < cols; j++) {
            dp[0][j] = 1;
        }
        
        for(int i = 1; i < rows; i++) {
            dp[i][0] = 1;
        }
        
        for(int i = 1; i < rows; i++) {
            for(int j = 1; j < cols; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[rows-1][cols-1];
    }
}
