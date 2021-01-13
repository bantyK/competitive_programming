import java.util.*;

// 881 https://leetcode.com/problems/boats-to-save-people/
public class NumRescueBoats {
    public static void main(String[] args) {
        NumRescueBoats obj = new NumRescueBoats();

        // System.out.println(obj.numRescueBoats(new int[] { 3, 3, 4, 1, 5 }, 5) == 4);
        // System.out.println(obj.numRescueBoats(new int[] { 5, 5, 5, 5, 5 }, 5) == 5);
        // System.out.println(obj.numRescueBoats(new int[] { 5, 5, 5, 5, 5 }, 10) == 3);
        // System.out.println(obj.numRescueBoats(new int[] { 5, 5, 5, 5, 5 }, 100) ==
        // 1);
        // System.out.println(obj.numRescueBoats(new int[] { 1, 4, 3, 6, 3 }, 10) == 2);
        System.out.println(obj.numRescueBoats(new int[] { 1, 5, 2, 4 }, 5) == 3);
        System.out.println(obj.numRescueBoats(new int[] { 5, 1, 4, 2 }, 6) == 2);
    }

    // O(NlogN) approach
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int numBoats = 0;
        while (left <= right) {
            numBoats++;
            if (left == right)
                break;
            if (people[left] + people[right] <= limit)
                left++;
            right--;
        }
        return numBoats;
    }
}