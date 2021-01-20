import java.io.*;

// https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/range-query-2/
public class BinaryQueries {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");

        int n = Integer.parseInt(line1[0]);
        int q = Integer.parseInt(line1[1]);

        String[] arr = br.readLine().split(" ");

        for (int i = 0; i < q; i++) {
            String[] line2 = br.readLine().split(" ");

            if (line2[0].equals("0")) {
                int r = Integer.parseInt(line2[2]) - 1;
                if (arr[r].equals("0"))
                    System.out.println("EVEN");
                else
                    System.out.println("ODD");
            } else if (line2[0].equals("1")) {
                int index = Integer.parseInt(line2[1]) - 1;
                arr[index] = arr[index].equals("0") ? "1" : "0";
            }
        }
    }

}
