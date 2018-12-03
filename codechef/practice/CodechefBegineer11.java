import java.util.Scanner;

// https://www.codechef.com/problems/LUCKFOUR
class LuckyFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);    
        int t = scanner.nextInt();
        scanner.nextLine();
        String input;
        int count4 = 0;
        for(int i = 0; i < t; i++) {
            input = scanner.nextLine();
            count4 = 0;
            for(int j = 0; j < input.length(); j++) {
                if(input.charAt(i) == '4') {
                    count4 += 1;
                }
            }

            System.out.println(count4);
        }
    }
}