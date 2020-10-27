import java.util.*;

//799 https://leetcode.com/problems/champagne-tower/
public class CampagneTower {
    public static void main(String[] args) {
        CampagneTower obj = new CampagneTower();
        System.out.println(obj.champagneTower2(10, 4, 2));
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        if (poured == 0) return 0;

        List<Double> list = new ArrayList<>();
        list.add(poured * 1.0);

        while (query_row-- > 0) {
            List<Double> temp = new ArrayList<>();
            temp.add(Math.max(0, (list.get(0) - 1) / 2.0));

            for (int i = 1; i < list.size(); i++) {
                // this value could be negative if there is not enough poured value
                // get the previous values and divide by 2
                double valLeft = Math.max(0, (list.get(i - 1) - 1) / 2.0);
                double valRight = Math.max(0, (list.get(i) - 1) / 2.0);

                temp.add(valLeft + valRight);
            }
            temp.add(temp.get(0));

            list = new ArrayList<>(temp); // this row will be used again to create the next row
        }

        return Math.max(0, list.get(query_glass));
    }

    public double champagneTower2(int poured, int query_row, int query_glass) {
        if (poured == 0) return 0;

        double[][] glasses = new double[101][101];
        glasses[0][0] = poured;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (glasses[i][j] >= 1) {
                    glasses[i + 1][j] += (glasses[i][j] - 1) / 2.0;
                    glasses[i + 1][j + 1] += (glasses[i][j] - 1) / 2.0;
                    glasses[i][j] = 1;
                }
            }
        }
        return glasses[query_row][query_glass];
    }
}
