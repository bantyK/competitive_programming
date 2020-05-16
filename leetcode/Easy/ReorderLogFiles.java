import java.util.*;

//937 https://leetcode.com/problems/reorder-data-in-log-files/
public class ReorderLogFiles {
    public static void main(String[] args) {
        ReorderLogFiles obj = new ReorderLogFiles();

//        String[] res = obj.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"});
        String[] res = obj.reorderLogFiles(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"});
        System.out.println(Arrays.toString(res));
    }

    List<String> letterLogs;
    List<String> digitLogs;

    public String[] reorderLogFiles(String[] logs) {
        letterLogs = new ArrayList<>();
        digitLogs = new ArrayList<>();

        seperateLogs(logs);
//        System.out.println(letterLogs);
//        System.out.println(digitLogs);

        letterLogs.sort((str1, str2) -> {
            String substr1 = str1.substring(str1.indexOf(" ") + 1);
            String substr2 = str2.substring(str2.indexOf(" ") + 1);

            if(substr1.equals(substr2)) {
                return str1.split(" ")[0].compareTo(str2.split(" ")[0]);
            }

            return substr1.compareTo(substr2);
        });


        int index = 0;

        for(String letterLog : letterLogs) {
            logs[index++] = letterLog;
        }

        for(String digitLog : digitLogs) {
            logs[index++] = digitLog;
        }

        return logs;
    }

    private List<String> seperateLogs(String[] logs) {

        for (String log : logs) {
            if (!containsDigit(log)) {
                letterLogs.add(log);
            } else {
                digitLogs.add(log);
            }
        }

        return letterLogs;
    }

    private boolean containsDigit(String log) {
        log = log.substring(log.indexOf(" ") + 1);
//        System.out.println(log);
        for (char c : log.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }


}
