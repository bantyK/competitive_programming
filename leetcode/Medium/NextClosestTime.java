import java.util.*;

//681 https://leetcode.com/problems/next-closest-time/
public class NextClosestTime {
    public static void main(String[] args) {
        NextClosestTime obj = new NextClosestTime();
        System.out.println(obj.nextClosestTime("19:34"));
    }

    public String nextClosestTime(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));

        HashSet<Integer> digits = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            if (i == 2) continue;
            digits.add(time.charAt(i) - '0');
        }

        while (true) {
            minutes = (minutes + 1) % ((23 * 60) + 59);
            int[] arr = convertMinutes(minutes);
            boolean valid = true;

            for (int num : arr) {
                if (!digits.contains(num)) {
                    valid = false;
                }
            }
            if (valid) return arr[0] + "" + arr[1] + ":" + arr[2] + "" + arr[3];
        }
    }

    private int[] convertMinutes(int minutes) {
        int[] arr = new int[4];
        int hour = minutes / 60;
        int min = minutes % 60;
        arr[0] = hour / 10; // first digit
        arr[1] = hour % 10; // second digit

        arr[2] = min / 10; // first digit
        arr[3] = min % 10; // second digit

        return arr;
    }
}
