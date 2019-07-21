package com.company.leet;

public class RobotMovement {

    public static void main(String[] args) {
        RobotMovement obj = new RobotMovement();
        System.out.println(obj.judgeCircle("LRRLUULRDD"));
    }

    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    y = y + 1;
                    break;
                case 'D':
                    y = y - 1;
                    break;
                case 'L':
                    x = x + 1;
                    break;
                case 'R':
                    x = x - 1;
                    break;
            }
        }
        return (x == 0 && y == 0);
    }
}
