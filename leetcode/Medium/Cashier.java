import java.util.*;

// 1357 https://leetcode.com/problems/apply-discount-every-n-orders/
public class Cashier {
    private int numCustomer;
    private int discount;
    private int n;
    private Map<Integer, Integer> productPriceMap;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        productPriceMap = new HashMap<>();

        for (int i = 0; i < products.length; i++) {
            productPriceMap.put(products[i], prices[i]);
        }

        numCustomer = 0;
    }

    public static void main(String[] args) {
        final int[] products = {1, 2, 3, 4, 5, 6, 7};
        final int[] prices = {100, 200, 300, 400, 300, 200, 100};
        Cashier obj = new Cashier(3, 50, products, prices);
        System.out.println(obj.getBill(new int[]{1, 2}, new int[]{1, 2}));
        System.out.println(obj.getBill(new int[]{3, 7}, new int[]{10, 10}));
        System.out.println(obj.getBill(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 1, 1, 1, 1, 1, 1}));
        System.out.println(obj.getBill(new int[]{4}, new int[]{10}));
        System.out.println(obj.getBill(new int[]{7, 3}, new int[]{10, 10}));
        System.out.println(obj.getBill(new int[]{7, 5, 3, 1, 6, 4, 2}, new int[]{10, 10, 10, 9, 9, 9, 7}));
    }

    public double getBill(int[] product, int[] amount) {
        numCustomer++;

        double bill = 0;

        for (int i = 0; i < product.length; i++) {
            bill += amount[i] * productPriceMap.get(product[i]);
        }

        if (numCustomer == n) {
            bill = bill - (discount * bill) / 100;
            numCustomer = 0;
        }

        return bill;
    }

}
