import java.util.*;

//Leetcode359 https://leetcode.com/problems/logger-rate-limiter/
public class LoggerRateLimiter {

    class Logger {
        Map<String, Integer> messageTimestampMap;

        public Logger() {
            messageTimestampMap = new HashMap<>();
        }


        public boolean shouldPrintMessage(int timestamp, String message) {
            if (messageTimestampMap.containsKey(message)) {
                long lastShownTimestamp = messageTimestampMap.get(message);
                if (timestamp - lastShownTimestamp > 10) {
                    messageTimestampMap.put(message, timestamp);
                    return true;
                }
                return false;
            } else {
                messageTimestampMap.put(message, timestamp);
                return true;
            }
        }
    }

}
