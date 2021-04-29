import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// 1229 https://leetcode.com/problems/meeting-scheduler/
public class MeetingScheduler {
    public static void main(String[] args) {
        MeetingScheduler obj = new MeetingScheduler();
        int[][] slots2 = new int[1000][2];
        int[][] slots1 = new int[1000][2];
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            slots1[i][0] = 1 + random.nextInt(10000 - 1);
            slots1[i][1] = 1 + random.nextInt(10000 - 1);

            slots2[i][0] = 1 + random.nextInt(10000 - 1);
            slots2[i][1] = 1 + random.nextInt(10000 - 1);
        }


        System.out.println(obj.minAvailableDuration(slots1, slots2, 12));
    }

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int i = 0, j = 0;

        while (i < slots1.length && j < slots2.length) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);
            if (start + duration <= end) {
                return Arrays.asList(start, start + duration);
            }
            if (slots1[i][1] == slots2[j][1]) {
                ++i;
                ++j;
            } else if (slots1[i][1] > slots2[j][1]) {
                // j cannot overlap with other slots of i, so we can move j
                j++;
            } else {
                // i cannot overlap with other slots of j, so move i
                i++;
            }
        }
        return Collections.emptyList();
    }

}