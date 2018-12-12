import java.util.*;

class TypingTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int n;
        String[] words;
        for (int i = 0; i < t; i++) {
            n = scanner.nextInt();
            scanner.nextLine();
            words = new String[n];
            for (int j = 0; j < n; j++) {
                words[j] = scanner.nextLine();
            }
            System.out.println(getTotalTime(words));
        }
    }

    static int getTotalTime(String[] words) {
        int totalTimeTaken = 0;
        int timeTakenForSingleWord;
        Map<String, Integer> timeTaken = new HashMap<>();
        for (String word : words) {
            if (!timeTaken.containsKey(word)) {
                timeTakenForSingleWord = timeForSingleWord(word);
                timeTaken.put(word, timeTakenForSingleWord);
                totalTimeTaken += timeTakenForSingleWord;
            } else {
                totalTimeTaken += timeTaken.get(word) / 2;
            }
        }
        return totalTimeTaken;
    }

    static int timeForSingleWord(String word) {
        int totalTime = 2;
        Map<Character, String> handsMap = getHandsMap();
        String currentHand = handsMap.get(word.charAt(0));
        for (int i = 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentHand.equals(handsMap.get(currentChar))) {
                // same hand, add 0.4 sec
                totalTime += 4;
            } else {
                // different hand, add 0.2 sec
                totalTime += 2;
            }
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