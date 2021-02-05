import java.util.*;

//475 https://leetcode.com/problems/heaters/
public class Heaters {
    public static void main(String[] args) {
        Heaters obj = new Heaters();
        System.out.println(obj.findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(obj.findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
        System.out.println(obj.findRadius(new int[]{1, 5}, new int[]{2}));
        System.out.println(obj.findRadius(new int[]{1, 5}, new int[]{10}));
    }

    // Using Binary search
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int res = Integer.MIN_VALUE;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = -(index + 1);
            }
            int distanceFromHeaterToLeft = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int distanceFromHeaterToRight = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            res = Math.max(res, Math.min(distanceFromHeaterToLeft, distanceFromHeaterToRight));
        }

        return res;
    }

    // Using TreeSet
    public int findRadius2(int[] houses, int[] heaters) {
        TreeSet<Integer> map = new TreeSet<>();
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < heaters.length; i++) {
            map.add(heaters[i]);
        }

        for (int house : houses) {
            int leftDistance = Integer.MAX_VALUE;
            int rightDistance = Integer.MAX_VALUE;

            Integer floorEntry = map.floor(house);
            if (floorEntry != null) {
                leftDistance = house - floorEntry;
            }

            Integer ceiligKey = map.ceiling(house);
            if (ceiligKey != null) {
                rightDistance = ceiligKey - house;
            }

            res = Math.max(res, Math.min(leftDistance, rightDistance));
        }
        return res;
    }

}
