import java.util.*;

//1276 https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
public class NumBurgers {
    public static void main(String[] args) {
        NumBurgers obj = new NumBurgers();
        System.out.println(obj.numOfBurgers(6, 2));
    }

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> result = new ArrayList<>();

        if(tomatoSlices % 2 == 1)  return result;

        int jumbo = tomatoSlices - (2 * cheeseSlices);

        if(jumbo < 0 || jumbo % 2 == 1) return result;

        jumbo = jumbo / 2;

        int small = cheeseSlices - jumbo;

        if(small < 0) return result;

        result.add(jumbo);
        result.add(small);

        return result;

    }
}
