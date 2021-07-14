//640 https://leetcode.com/problems/solve-the-equation/
public class SolveEquation {

    public static void main(String[] args) {
        SolveEquation obj = new SolveEquation();
        System.out.println(obj.solveEquation("x+5-3+x=6+x-2").equals("x=2"));
        System.out.println(obj.solveEquation("x=2x+4").equals("x=-4"));
        System.out.println(obj.solveEquation("x=4+2x").equals("x=-4"));
        System.out.println(obj.solveEquation("x=12-2x").equals("x=4"));
        System.out.println(obj.solveEquation("6+x-2x+41=14x+4-32").equals("x=5"));
        System.out.println(obj.solveEquation("100x+500=50x+1000-500").equals("x=0"));
        System.out.println(obj.solveEquation("x+2=x-2").equals("No solution"));
        System.out.println(obj.solveEquation("x+2=x+2").equals("Infinite solutions"));
        System.out.println(obj.solveEquation("3434x-1=3434-1").equals("x=1"));
        System.out.println(obj.solveEquation("2x+3x-6x=x+2").equals("x=-1"));
        System.out.println(obj.solveEquation("2x=x").equals("x=0"));
        System.out.println(obj.solveEquation("x=x+2").equals("No solution"));
        System.out.println(obj.solveEquation("-x=-1").equals("x=1"));
        System.out.println(obj.solveEquation("x=-1").equals("x=-1"));
        System.out.println(obj.solveEquation("0x=0").equals("Infinite solutions"));
    }

    public String solveEquation(String equation) {
        String[] split = equation.split("=");
        String lhs = split[0];
        String rhs = split[1];

        int[] left = solve(lhs);
        int[] right = solve(rhs);

        if (left[0] == right[0]) {
            if (left[1] == right[1]) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }

        return "x=" + (right[1] - left[1]) / (left[0] - right[0]);
    }

    private int[] solve(String equation) {
        int coefficient = 0;
        int constant = 0;
        int sign = 1;
        int value = 0;
        int num = 1;
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (ch == 'x') {
                coefficient += (value == 0) ? (num == 0) ? 0 : sign : (sign * value);
                value = 0;
            } else if (ch == '-') {
                constant += (value > 0) ? (sign * value) : 0;
                sign = -1;
                value = 0;
            } else if (ch == '+') {
                constant += (value > 0) ? (sign * value) : 0;
                sign = 1;
                value = 0;
            } else if (Character.isDigit(ch)) {
                num = ch - '0';
                value = (value * 10) + num;
            }
        }
        if (value > 0) {
            constant += (sign * value);
        }
        return new int[]{coefficient, constant};
    }

}