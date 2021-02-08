import java.util.*;
import java.util.stream.Collectors;

// 1333 https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
public class SortRestaurants {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ids = new ArrayList<>();
        Map<Integer, int[]> restaurantMap = new HashMap<>();

        for (int[] res : restaurants) {
            if (res[2] >= veganFriendly && res[3] <= maxPrice && res[4] <= maxDistance) {
                restaurantMap.put(res[0], res);
                ids.add(res[0]);
            }
        }

        Collections.sort(ids, (id1, id2) -> {
            int rating1 = restaurantMap.get(id1)[1];
            int rating2 = restaurantMap.get(id2)[1];

            if (rating1 == rating2) {
                return id2 - id1;
            }

            return rating2 - rating1;
        });

        return ids;
    }

    public List<Integer> filterRestaurantsStreamsSol(int[][] restaurants, int veganFriendly, int maxPrice,
            int maxDistance) {
        List<int[]> filtered = Arrays.stream(restaurants).filter(restaurant -> restaurant[2] >= veganFriendly)
                .filter(restaurant -> restaurant[3] <= maxPrice).filter(restaurant -> restaurant[4] <= maxDistance)
                .collect(Collectors.toList());

        Collections.sort(filtered, (r1, r2) -> {
            if (r1[1] == r2[1]) {
                return r2[0] - r1[0];
            }
            return r2[1] - r1[1];
        });

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < filtered.size(); i++) {
            res.add(filtered.get(i)[0]);
        }
        return res;
    }
}
