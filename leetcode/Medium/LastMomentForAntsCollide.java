public class LastMomentForAntsCollide {
    // Consider it as nothing happens if the ants collide
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxDistance = Integer.MIN_VALUE;
        
        for(int i : left) {
            maxDistance = Math.max(maxDistance, i);
        }
        
        for(int i : right) {
            maxDistance = Math.max(maxDistance, n - i);
        }
        
        return maxDistance;
    }
}

