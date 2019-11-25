import java.util.*;
//1268.  https://leetcode.com/problems/search-suggestions-system/
public class SuggestedProducts {
    public static void main(String[] args) {
        List<List<String>> res = suggestedProducts(new String[]{"bags","baggage","banner","box","cloths"}, "bags");
        for(List<String> r : res) {
            System.out.println(r);
        }
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> results = new ArrayList<>();

        if(products == null || products.length == 0) return results;

        Arrays.sort(products); 

        for(int i = 0; i < searchWord.length(); i++) {
            List<String> temp = new ArrayList<>();
            int count = 0;
            for(String product : products) {
                if(count < 3 && product.substring(0, i).equals(searchWord.substring(0, i))) {
                    temp.add(product);
                    count++;
                }
            }

            results.add(temp);
        }

        return results;
    }
}