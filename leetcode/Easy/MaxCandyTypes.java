import java.util.*;
//575 https://leetcode.com/problems/distribute-candies/

public class MaxCandyTypes {
    public int distributeCandies(int[] candyTypes) {
        Set<Integer> set = new HashSet<>();
        int uniqueCandyCount =0;
        int n = candyTypes.length;

        for(int type : candyTypes) {
            if(set.add(type)) {
                uniqueCandyCount++;
            }
        }

        return Math.min(n / 2, uniqueCandyCount);
        
    }
}
