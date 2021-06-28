import java.util.*;

// 1912 https://leetcode.com/problems/design-movie-rental-system/
public class MovieRentalDesign {
    public static void main(String[] args) {
        MovieRentingSystem obj = new MovieRentingSystem(3, new int[][]{{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}});
        // ["MovieRentingSystem","search","rent","rent","drop","report","search"]
        //[[3,[[0,1,5],[0,2,6],[0,3,7],[1,1,4],[1,2,7],[2,1,5]]],[1],[0,1],[1,2],[1,2],[],[2]]
        //["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
        //[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
        //["MovieRentingSystem","rent","search","search","report","rent","rent","report","report","search","search","rent","rent","search","drop","drop","drop","drop","rent","report","report","rent","drop","search","report","drop","report","drop","rent","report","search","search","rent","rent","report","report","drop","report","report","drop","report","drop","rent","drop","search","rent","search","drop","rent","drop","report","rent","drop","rent","rent","drop","report","report","report","report","rent","drop","report","drop","rent","search","drop","report","rent","search","search","report","rent","report","report","rent","report","report","search","rent","rent","search"]
        //[[69,[[16,4156,1511],[20,8501,8417],[34,7901,7776],[54,6691,9511],[44,8931,8434],[42,9640,5251],[22,4534,9161],[32,6506,6831],[13,8501,731],[4,7610,8474],[33,820,2341],[17,6490,1161],[29,7120,2703],[8,8723,7613],[38,9544,1804],[30,8723,1047],[1,5015,7763],[60,1625,2383],[29,3336,3542],[39,7535,6066],[1,9074,9400],[39,1625,7944],[26,9160,6874],[55,2465,888],[35,8530,6025]]],[32,6506],[8501],[6275],[],[30,8723],[8,8723],[],[],[6699],[115],[20,8501],[16,4156],[9447],[30,8723],[8,8723],[32,6506],[16,4156],[42,9640],[],[],[17,6490],[20,8501],[8175],[],[17,6490],[],[42,9640],[54,6691],[],[1625],[3291],[60,1625],[39,1625],[],[],[60,1625],[],[],[39,1625],[],[54,6691],[8,8723],[8,8723],[2260],[29,7120],[746],[29,7120],[38,9544],[38,9544],[],[1,9074],[1,9074],[54,6691],[39,1625],[54,6691],[],[],[],[],[26,9160],[26,9160],[],[39,1625],[42,9640],[9640],[42,9640],[],[29,7120],[5630],[1842],[],[16,4156],[],[],[1,9074],[],[],[7992],[4,7610],[29,3336],[1333]]
        System.out.println(obj.search(1));
        obj.rent(0, 1);
        obj.rent(1, 2);
        obj.drop(1, 2);
        System.out.println(obj.report());
    }


    static class Entry {
        int shopId;
        int movieId;
        int price;

        public Entry(int shopId, int movieId, int price) {
            this.shopId = shopId;
            this.movieId = movieId;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return shopId == entry.shopId &&
                    movieId == entry.movieId &&
                    price == entry.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(shopId, movieId, price);
        }
    }

    static class MovieRentingSystem {
        Map<Integer, Map<Integer, Integer>> shopMap; // shop -> {movie -> price}
        private Comparator<Entry> unrentedMapComparator = (o1, o2) -> {
            if (o1.price == o2.price) return o1.shopId - o2.shopId;
            return o1.price - o2.price;
        };
        private Comparator<Entry> rentedMapComparator = (o1, o2) -> {
            if (o1.price != o2.price) return o1.price - o2.price;
            if (o1.shopId != o2.shopId) return o1.shopId - o2.shopId;
            return o1.movieId - o2.movieId;
        };
        private Map<Integer, TreeSet<Entry>> unrentedMovieMap;
        private TreeSet<Entry> rentedMoviesSet;

        public MovieRentingSystem(int n, int[][] entries) {
            unrentedMovieMap = new HashMap<>();
            rentedMoviesSet = new TreeSet<>(rentedMapComparator);
            shopMap = new HashMap<>();
            for (int[] entry : entries) {
                int shopId = entry[0];
                int movieId = entry[1];
                int price = entry[2];

                shopMap.putIfAbsent(shopId, new HashMap<>());
                shopMap.get(shopId).put(movieId, price);

                unrentedMovieMap.putIfAbsent(movieId, new TreeSet<>(unrentedMapComparator));
                unrentedMovieMap.get(movieId).add(new Entry(shopId, movieId, price));
            }

        }

        public List<Integer> search(int movie) {
            List<Integer> res = new ArrayList<>();
            if (unrentedMovieMap.containsKey(movie)) {
                TreeSet<Entry> treeSet = unrentedMovieMap.get(movie);
                for (Entry entry : treeSet) {
                    res.add(entry.shopId);
                    if (res.size() == 5) break;
                }
            }
            return res;
        }

        public void rent(int shop, int movie) {
            // remove the movie from unrented map
            // put it in rented map

            if (unrentedMovieMap.containsKey(movie) && shopMap.containsKey(shop) && shopMap.get(shop).containsKey(movie)) {
                int price = shopMap.get(shop).get(movie);
                unrentedMovieMap.get(movie).remove(new Entry(shop, movie, price));
                rentedMoviesSet.add(new Entry(shop, movie, price));
            }
        }

        public void drop(int shop, int movie) {
            // remove it from rented and add it in unrented
            int price = shopMap.get(shop).get(movie);
            rentedMoviesSet.remove(new Entry(shop, movie, price));
            unrentedMovieMap.get(movie).add(new Entry(shop, movie, price));
        }

        public List<List<Integer>> report() {
            List<List<Integer>> res = new ArrayList<>();
            for (Entry entry : rentedMoviesSet) {
                res.add(Arrays.asList(entry.shopId, entry.movieId));
                if (res.size() == 5) break;
            }
            return res;
        }
    }

}