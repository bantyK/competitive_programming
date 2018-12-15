// https://www.codehef.com/problems/H)1
import java.util.Scanner;
class PuzzleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        scanner.nextLine();
        int[][] input;
        String line;
        String[] lineSplit;
        for(int i = 0; i < t; i++) {
            scanner.nextLine();
            input = new int[3][3];
            line = scanner.nextLine();
            lineSplit = line.split(" ");
            input[0][0] = Integer.parseInt(lineSplit[0]);
            input[0][1] = Integer.parseInt(lineSplit[1]);
            input[0][2] = Integer.parseInt(lineSplit[2]);
            scanner.nextLine();
            lineSplit = line.split(" ");
            input[1][0] = Integer.parseInt(lineSplit[0]);
            input[1][1] = Integer.parseInt(lineSplit[1]);
            input[1][2] = Integer.parseInt(lineSplit[2]);
            line = scanner.nextLine();
            lineSplit = line.split(" ");
            input[2][0] = Integer.parseInt(lineSplit[0]);
            input[2][1] = Integer.parseInt(lineSplit[1]);
            input[2][2] = Integer.parseInt(lineSplit[2]);
            // swapTiles()
        
        }
    }
}