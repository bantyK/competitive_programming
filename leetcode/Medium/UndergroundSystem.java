import javafx.util.Pair;

import java.util.*;

// 1396 https://leetcode.com/problems/design-underground-system/
class UndergroundSystem {
    Map<Integer, Pair<String, Integer>> checkInMap;
    Map<String, Pair<Integer, Integer>> checkoutMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        checkoutMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<Integer, Integer> checkout = checkoutMap.get(id);
        Pair<String, Integer> checkinPair = checkInMap.get(id);

        int totalTime = t - checkinPair.getValue();

        String key = checkinPair.getKey() + "-" + stationName;
        checkoutMap.put(key, new Pair<>(checkout.getKey() + totalTime, checkout.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        Pair<Integer, Integer> pair = checkoutMap.get(key);
        return pair.getKey() / (1.0 * pair.getValue());
    }
}
/*
// Another solution using Javafx.pair

class UndergroundSystem {
    Map<Integer, Pair<String, Integer>> checkInMap;
    Map<String, Pair<Integer, Integer>> checkoutMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        checkoutMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkinPair = checkInMap.get(id);
        String key = checkinPair.getKey() + "-" + stationName;

        int totalTime = t - checkinPair.getValue();

        Pair<Integer, Integer> checkout = checkoutMap.getOrDefault(key, new Pair<>(0,0));

        checkoutMap.put(key, new Pair<>(checkout.getKey() + totalTime, checkout.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        Pair<Integer, Integer> pair = checkoutMap.get(key);
        return (double)pair.getKey() / pair.getValue();
    }
}


 */
