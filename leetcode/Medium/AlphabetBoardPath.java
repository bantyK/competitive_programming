import java.util.*;

//1138 https://leetcode.com/problems/alphabet-board-path/
public class AlphabetBoardPath {
    public static void main(String[] args) {
        AlphabetBoardPath obj = new AlphabetBoardPath();
//        System.out.println(obj.alphabetBoardPath("leet"));
        System.out.println(obj.alphabetBoardPath("yz"));
//        System.out.println(obj.alphabetBoardPath("code").equals("RR!DDRR!UUL!R!"));
    }

    public String alphabetBoardPath(String target) {
        StringBuilder builder = new StringBuilder();

//        Map<Character, int[]> positionMap = getPositionMap();

        int currentX = 0;
        int currentY = 0;

        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);

            int targetX = (ch - 'a') / 5;
            int targetY = (ch - 'a') % 5;

            while (currentX != targetX || currentY != targetY) {
                while ((currentX < 4 || currentY == 0) && currentX < targetX) {
                    // this for handling the
                    currentX++;
                    builder.append("D");
                }

                while (currentX > targetX) {
                    currentX--;
                    builder.append("U");
                }

                while (currentY > targetY) {
                    currentY--;
                    builder.append("L");
                }

                while (currentY < targetY) {
                    currentY++;
                    builder.append("R");
                }
            }

            builder.append("!");

        }

        return builder.toString();
    }

    private Map<Character, int[]> getPositionMap() {
        Map<Character, int[]> positionMap = new HashMap<>();

        positionMap.put('a', new int[]{0, 0});
        positionMap.put('b', new int[]{0, 1});
        positionMap.put('c', new int[]{0, 2});
        positionMap.put('d', new int[]{0, 3});
        positionMap.put('e', new int[]{0, 4});

        positionMap.put('f', new int[]{1, 0});
        positionMap.put('g', new int[]{1, 1});
        positionMap.put('h', new int[]{1, 2});
        positionMap.put('i', new int[]{1, 3});
        positionMap.put('j', new int[]{1, 4});

        positionMap.put('k', new int[]{2, 0});
        positionMap.put('l', new int[]{2, 1});
        positionMap.put('m', new int[]{2, 2});
        positionMap.put('n', new int[]{2, 3});
        positionMap.put('o', new int[]{2, 4});

        positionMap.put('p', new int[]{3, 0});
        positionMap.put('q', new int[]{3, 1});
        positionMap.put('r', new int[]{3, 2});
        positionMap.put('s', new int[]{3, 3});
        positionMap.put('t', new int[]{3, 4});

        positionMap.put('u', new int[]{4, 0});
        positionMap.put('v', new int[]{4, 1});
        positionMap.put('w', new int[]{4, 2});
        positionMap.put('x', new int[]{4, 3});
        positionMap.put('y', new int[]{4, 4});


        positionMap.put('z', new int[]{5, 0});

        return positionMap;
    }
}