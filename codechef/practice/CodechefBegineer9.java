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
        boolean inCheckMate = true;
        for (int i = 0; i < t; i++) {
            n = scanner.nextInt();
            scanner.nextLine();
            knightPositions = new Position[n];
            for (int j = 0; j < n; j++) {
                line = scanner.nextLine();
                knightPositions[j] = new Position(Long.parseLong(line.split(" ")[0]),
                        Long.parseLong(line.split(" ")[1]));
            }
            // get the king position
            line = scanner.nextLine();
            kingPosition = new Position(Long.parseLong(line.split(" ")[0]), Long.parseLong(line.split(" ")[1]));

            Set<Position> possibleKingPlaces = getKingPlaces(kingPosition);
            Set<Position> allKnightPlaces = getAllKnightPlaces(n, knightPositions);

            for (Position kingPos : possibleKingPlaces) {
                if (!allKnightPlaces.contains(kingPos)) {
                    inCheckMate = false;
                    break;
                }
            }

            if (inCheckMate) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        scanner.close();
    }

    // all the positions where all the knights can move
    private static Set<Position> getAllKnightPlaces(int numKnights, Position[] knightPositions) {
        Set<Position> allKnightPositions = new HashSet<>();
        long x, y;
        for (int i = 0; i < numKnights; i++) {
            x = knightPositions[i].x;
            y = knightPositions[i].y;
            allKnightPositions.addAll(getKnightPosition(x, y));
        }

        return allKnightPositions;
    }

    // returns all the position that a single knight can travel
    private static Set<Position> getKnightPosition(long x, long y) {
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
        long x = kingPosition.x;
        long y = kingPosition.y;
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
    long x;
    long y;

    Position(long x, long y) {
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