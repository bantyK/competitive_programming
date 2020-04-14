public class StringShifts {

    // Optimised solution
    public String stringShift(String s, int[][] shifts) {
        int leftShifts = 0, rightShifts = 0;
        for (int[] shift : shifts) {
            if (shift[0] == 0) {
                leftShifts += shift[1];
            } else {
                rightShifts += shift[1];
            }
        }
        int finalDirection = leftShifts > rightShifts ? 0 : 1;
        int finalAmount = Math.abs(leftShifts - rightShifts) % s.length();

        if (finalDirection == 0) {
            s = s.substring(finalAmount) + s.substring(0, finalAmount);
        } else {
            s = s.substring(s.length() - finalAmount) + s.substring(0, s.length() - finalAmount);
        }
        return s;
    }


    // Brute force
    public String stringShift2(String s, int[][] shifts) {
        for (int[] shift : shifts) {
            int amount = shift[1];
            switch (shift[0]) {
                case 0:
                    s = s.substring(amount) + s.substring(0, amount);
                    break;
                case 1:
                    s = s.substring(s.length() - amount) + s.substring(0, s.length() - amount);
                    break;
            }
        }
        return s;
    }
}
