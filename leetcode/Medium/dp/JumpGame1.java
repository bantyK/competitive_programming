// Greedy solution
public class JumpGame1 {    
    public boolean canJumpGreedy(int[] jumps) {
    	int maxReach = 0;

    	for(int i = 0; i < jumps.length; i++) {
    		maxReach = Math.max(maxReach, i + jumps[i]);
    		if(maxReach == i) {
    			// Cannot move ahead from this position
    			break;
    		}

    		return maxReach >= jumps.length - 1;
    	}
    }
}
