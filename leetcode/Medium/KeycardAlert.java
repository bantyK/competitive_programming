import java.util.*;

// 1604 https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
public class KeycardAlert {
    public static void main(String[] args) {
        KeycardAlert obj = new KeycardAlert();

        System.out.println(obj.alertNames(
                new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"},
                new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"}
        ));

        System.out.println(obj.alertNames(
                new String[]{"leslie", "leslie", "leslie", "clare", "clare", "clare", "clare"},
                new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"}
        ));
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> names = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(getTime(keyTime[i]));
        }

        for (String name : map.keySet()) {
            List<Integer> times = map.get(name);
            if (times.size() < 3) continue;
            Collections.sort(times);
            System.out.println(times);
            int left = 0;
            for (int right = 2; right < times.size(); right++) {
                if (times.get(right) - times.get(left) <= 60) {
                    names.add(name);
                    break;
                }
                left++;
            }
        }

        names.sort(String::compareTo);
        return names;
    }

    private int getTime(String s) {
        int h = Integer.parseInt(s.split(":")[0]);
        int m = Integer.parseInt(s.split(":")[1]);

        return h * 60 + m;
    }


}
