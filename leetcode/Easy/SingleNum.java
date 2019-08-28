package solutions;

public class SingleNum {

    public static void main(String[] args) {
        int i = new SingleNum().singleNumber(new int[]{4, 2, 1, 2, 1});
        System.out.println(i);
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums)
            single = single ^ num;
        return single;
    }
}
