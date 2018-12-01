import java.util.HashMap;
import java.util.Scanner;

// https://www.codechef.com/problems/EVENT

class EventSolution {

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
        if (diff >= 0) {
            minDays = diff + 1;
        } else {
            minDays = 7 + diff + 1;
        }

        return minDays;
    }

    static void getNumberOfDays(int minDays, int L, int R) {
        int start = getStart(minDays, L);
        int originalStartValue = start; 
        int count = 0;
        while (start <= R) {
            count += 1;
            start += 7;

            if (count > 1) {
                System.out.println("many");
                return;
            }
        }

        if (count == 1) {
            System.out.println(originalStartValue);
        } else {
            System.out.println("impossible");
        }
    }

    static void printPossibleDays(String startDay, String endDay, int L, int R) {
        int min = minNumberOfDays(startDay, endDay);
        getNumberOfDays(min, L, R);
    }

    static int getStart(int min, int L) {
        if (min >= L)
            return min;
        else
            return min + 7;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        Input[] inputs = new Input[testCases];
        for (int i = 0; i < testCases; i++) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            Input input = new Input(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
            inputs[i] = input;
        }

        for (int i = 0; i < testCases; i++) {
            printPossibleDays(inputs[i].startDay, inputs[i].endDay, inputs[i].L, inputs[i].R);
        }
    }
}

class Input {
    String startDay;
    String endDay;
    int L;
    int R;

    Input(String s, String e, int l, int r) {
        startDay = s;
        endDay = e;
        L = l;
        R = r;
    }
}