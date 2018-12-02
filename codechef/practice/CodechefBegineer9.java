import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class CodechefBegineer9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int n; // number of knights
        Position[] knightPositions;
        Position kingPosition;
        String line; // store space separated input for king and knight position
        boolean inCheckMate;
        boolean kingInCheck;
        for (int i = 0; i < t; i++) {
            inCheckMate = true;
            kingInCheck = false;
            n = scanner.nextInt();
            scanner.nextLine();
            knightPositions = new Position[n];
            for (int j = 0; j < n; j++) {
                line = scanner.nextLine();
                knightPositions[j] = new Position(Integer.parseInt(line.split(" ")[0]),
                        Integer.parseInt(line.split(" ")[1]));
            }
            // get the king position
            line = scanner.nextLine();
            kingPosition = new Position(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));

            Set<Position> possibleKingPlaces = getKingPlaces(kingPosition);
            Set<Position> allKnightPlaces = getAllKnightPlaces(n, knightPositions);

            for (Position position : allKnightPlaces) {
                if (position.equals(kingPosition)) {
                    kingInCheck = true;
                    break;
                }
            }

            if (kingInCheck) {
                for (Position kingPos : possibleKingPlaces) {
                    if (allKnightPlaces.contains(kingPos)) {
                        inCheckMate = true;
                        break;
                    }
                }
                if (inCheckMate) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println("NO");
            }
        }
        scanner.close();
    }

    // all the positions where all the knights can move
    private static Set<Position> getAllKnightPlaces(int numKnights, Position[] knightPositions) {
        Set<Position> allKnightPositions = new HashSet<>();
        int x, y;
        for (int i = 0; i < numKnights; i++) {
            x = knightPositions[i].x;
            y = knightPositions[i].y;
            allKnightPositions.addAll(getKnightPosition(x, y));
        }

        return allKnightPositions;
    }

    // returns all the position that a single knight can travel
    private static Set<Position> getKnightPosition(int x, int y) {
        Set<Position> knightPosition = new HashSet<>();
        knightPosition.clear();
        knightPosition.add(new Position(x + 2, y + 1));
        knightPosition.add(new Position(x + 1, y + 2));
        knightPosition.add(new Position(x + 2, y - 1));
        knightPosition.add(new Position(x + 1, y - 2));
        knightPosition.add(new Position(x - 1, y - 2));
        knightPosition.add(new Position(x - 2, y - 1));
        knightPosition.add(new Position(x - 1, y + 2));
        knightPosition.add(new Position(x - 2, y + 1));
        return knightPosition;
    }

    // returns the coordinates of places where the king can move
    private static Set<Position> getKingPlaces(Position kingPosition) {
        int x = kingPosition.x;
        int y = kingPosition.y;
        Set<Position> positionSet = new HashSet<>();
        positionSet.clear();
        positionSet.add(new Position(x + 1, y)); // right
        positionSet.add(new Position(x - 1, y)); // left
        positionSet.add(new Position(x, y + 1)); // top
        positionSet.add(new Position(x, y - 1)); // down
        positionSet.add(new Position(x + 1, y + 1)); // top-right
        positionSet.add(new Position(x + 1, y - 1)); // bottom-right
        positionSet.add(new Position(x - 1, y + 1)); // top-left
        positionSet.add(new Position(x - 1, y - 1)); // bottom-left

        return positionSet;
    }
}

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Position))
            return false;
        if (obj == this)
            return true;
        Position pos = (Position) obj;
        return this.x == pos.x && this.y == pos.y;
    }

    @Override
    public int hashCode() {
        return (int) (this.x + this.y);
    }
}