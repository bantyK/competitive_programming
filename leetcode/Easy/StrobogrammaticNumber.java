import java.util.*;

// 246 https://leetcode.com/problems/strobogrammatic-number/
public class StrobogrammaticNumber {

    public static void main(String[] args) {
        StrobogrammaticNumber obj = new StrobogrammaticNumber();
        System.out.println(obj.isStrobogrammatic("111111"));
        System.out.println(obj.isStrobogrammatic("8888888"));
        System.out.println(obj.isStrobogrammatic("018018018"));
        System.out.println(obj.isStrobogrammatic("696969690"));
        System.out.println(obj.isStrobogrammatic("696969069"));
    }

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> mirrorMap = new HashMap<>();


        /*
            When we rotate by 180 degrees,
            the left side becomes right and right becomes left
            if after rotation, the number needs to be same, than the number at left end and number
            at right end should be mirror images of each other.
        */

        mirrorMap.put('1', '1');
        mirrorMap.put('0', '0');
        mirrorMap.put('8', '8');
        mirrorMap.put('6', '9');
        mirrorMap.put('9', '6');

        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!mirrorMap.containsKey(num.charAt(left))) return false;
            if (mirrorMap.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}