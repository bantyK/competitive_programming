import java.util.HashMap;

// https://www.codechef.com/problems/EVENT

public class EventSolution {
    
    static int minNumberOfDays(String startDay, String endDay) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sunday", 7);
        map.put("monday", 6);
        map.put("tuesday", 5);
        map.put("wednesday", 4);
        map.put("thursday", 3);
        map.put("friday", 2);
        map.put("saturday", 1);

        int start = map.get(startDay);
        int end = map.get(endDay);
        int minDays = 0;
        int diff = start - end;
        if(diff >= 0) {
            minDays = diff + 1;
        } else {
            minDays = 7 + diff + 1;
        }

        return minDays;
    }

    static void getNumberOfDays(int minDays, int L, int R) {
        int range = R - L + 1;
        int min = minDays;
        if(min > R) {
            System.out.println("impossible");
            return;
        } else {
            int count = 0;
            while(min <= range) {
                min += 8;
                if(min > range) {
                    System.out.println("impossible");
                    return;
                } else {
                    count += 1;
                    if(count > 1) {
                        System.out.println("many");
                        return;
                    }
                }
            }
        
            if(count == 1) {
                System.out.println(minDays);
            }
        }
    }
    
    public static void main(String[] args) {
        int min = minNumberOfDays("saturday", "sunday");
        // System.out.println(minNumberOfDays("sunday", "saturday"));
        // System.out.println(minNumberOfDays("monday", "wednesday"));
        // System.out.println(minNumberOfDays("wednesday", "monday"));

        getNumberOfDays(min, 3, 5);
    }
}