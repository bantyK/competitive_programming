package solutions.easy;

import solutions.util.ArrayUtils;

import java.util.*;

// 1103:  https://leetcode.com/problems/distribute-candies-to-people/
public class DistributeCandies {
    public static void main(String[] args) {
        DistributeCandies obj = new DistributeCandies();
        ArrayUtils.printArray(obj.distributeCandies(100, 3));
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        Arrays.fill(result, 0);
        int currentCount = 1;
        int index = 0;

        while(candies > 0) {
            if(candies < currentCount) {
                result[index] += candies;
                break;
            } else {
                result[index] += currentCount;
                candies -= currentCount;
                currentCount += 1;
                index = (index + 1) % num_people;
            }
        }

        return result;
    }
}
