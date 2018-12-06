//https://www.codechef.com/problems/ROBOGAME
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class RoboGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        String s;
        for(int i = 0; i < t; i++) {
            s = scanner.nextLine();
            List<Integer> positions = getPositions(s);
            System.out.println(safeOrUnsafe(positions));
        }
    }

    static List<Integer> getPositions(String input) {
        List<Integer> positions = new ArrayList<>();
        for(char c : input.toCharArray()) {
            if(c != '.') {
                positions.add(Integer.parseInt(Character.toString(c)));
            }
        }
        return positions;
    }

    static String safeOrUnsafe(List<Integer> positions) {
        if(positions.size() <= 1) {
            return "safe";
        }
        return "safe";
    }
}