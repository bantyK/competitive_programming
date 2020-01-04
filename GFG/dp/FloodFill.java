// https://practice.geeksforgeeks.org/problems/flood-fill-algorithm/0
class GFG {
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();

        for (int k = 0; k < t; k++) {
            String line = s.nextLine();
            String[] vals = line.split(" ");

            int N = Integer.parseInt(vals[0]);
            int M = Integer.parseInt(vals[1]);
            int[][] mat = new int[N][M];


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    mat[i][j] = s.nextInt();
                }
            }
            s.nextLine();

            line = s.nextLine();
            int x = Integer.parseInt(line.split(" ")[0]);
            int y = Integer.parseInt(line.split(" ")[1]);
            int K = Integer.parseInt(line.split(" ")[2]);

//            for(int[] arr : mat) {
//                System.out.println(Arrays.toString(arr));
//            }

            floodFill(mat, x, y, mat[x][y], K);

            StringBuilder builder = new StringBuilder();
            for(int[] arr : mat) {
                for(int num : arr) {
                    builder.append(num).append(" ");
                }
            }

            System.out.println(builder.toString().trim());
        }

    }

    private static void floodFill(int[][] mat, int x, int y, int num, int k) {
        if (x < 0 || x >= mat.length) return;
        if (y < 0 || y >= mat[x].length) return;
        if (mat[x][y] != num) return;

        mat[x][y] = k;

        floodFill(mat, x + 1, y, num, k);
        floodFill(mat, x - 1, y, num, k);
        floodFill(mat, x, y + 1, num, k);
        floodFill(mat, x, y - 1, num, k);
    }
}
