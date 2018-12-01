// use this class as a template, rename it accordingly
import java.util.Scanner;

class CodechefBegineer7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rounds = scanner.nextInt();
        scanner.nextLine();
        int winnerPlayer = 0;
        int prevp1score = 0, prevp2score = 0;
        int currp1score, currp2score;
        int maxLead = 0, lead = 0;
        String line;
        for(int i = 0; i < rounds; i++) {
            line = scanner.nextLine();
            currp1score = prevp1score + Integer.parseInt(line.split(" ")[0]);
            currp2score = prevp2score + Integer.parseInt(line.split(" ")[1]);
            lead = Math.abs(currp1score - currp2score);
            if(lead > maxLead) {
                maxLead = lead;
                winnerPlayer = currp1score > currp2score ? 1 : 2;
            }
            prevp1score = currp1score;
            prevp2score = currp2score;
        }
        scanner.close();
        System.out.println(winnerPlayer + " " + maxLead);
    }
}

class Input {
    int p1score;
    int p2score;

    Input(int p1, int p2) {
        p1score = p1;
        p2score = p2;
    }
}