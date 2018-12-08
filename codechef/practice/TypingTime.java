import java.util.*;

class TypingTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        String input;
        for(int i = 0; i < t; i++) {
            input = scanner.nextLine();
            System.out.println(getTotalTime(input));
        }
    }

    static double getTotalTime(String word) {
        double totalTime = 0;
        Map<Character, String> handsMap = getHandsMap();
        String currentHand = handsMap.get(word.charAt(0));
        totalTime = 0.2;
        for(int i = 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            double timeToAdd;
            if(currentHand.equals(handsMap.get(currentChar))) {
                // same hand, add 0.4 sec
                timeToAdd = 0.4;
            } else {
                // different hand, add 0.2 sec
                timeToAdd = 0.2;
            }
            //check if this character already appeared, half the time it took before

            totalTime += timeToAdd;
            currentHand = handsMap.get(currentChar);
        }
        return totalTime;
    }

    static Map<Character, String> getHandsMap() {
        Map<Character, String> handsMap = new HashMap<>();
        handsMap.put('d', "left");
        handsMap.put('f', "left");
        handsMap.put('j', "right");
        handsMap.put('k', "right");

        return handsMap;
    }
}