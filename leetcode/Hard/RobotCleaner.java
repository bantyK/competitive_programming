package dfs;

import java.util.*;

//489 https://leetcode.com/problems/robot-room-cleaner/
interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();

    void turnRight();

    // Clean the current cell.
    void clean();
}

public class RobotCleaner {
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0, new HashSet<String>());
    }

    private void dfs(Robot r, int row, int col, int direction, Set<String> visited) {
        String key = row + "-" + col;
        if (visited.contains(key)) {
            // this position is already cleaned, return from here
            return;
        }

        r.clean(); // first clean the room
        visited.add(key); // mark that the room has cleaned

        for (int i = 0; i < 4; i++) {
            // this loop will go over the 4 directions at any given position
            if (r.move()) {
                // robot can move in the direction where it is pointing
                int nextR = row; // cannot change the row and col because for other iteration we will need this same values.
                int nextC = col;

                switch (direction) {
                    case 0:
                        nextR -= 1;
                        break;
                    case 90:
                        nextC += 1;
                        break;
                    case 180:
                        nextR += 1;
                        break;
                    case 270:
                        nextC -= 1;
                        break;
                }

                dfs(r, nextR, nextC, direction, visited);// pass the same direction because we will be exploring this direction

                //IMP: It is important that when after the recursion, robot moves to its parent cell, it must point to the same
                // direction which it was pointing before going to the next cell. The reason being, we have to change direction
                // If the robot points to some other direction then we will miss exploring some direction

                // At this point robot has explored all the cells it can visit and time to go back to the previous cell
                // turn the robot to the direction opposite to current direction, for this rotate the robot 2 times to left
                r.turnLeft();
                r.turnLeft();
                // Robot is pointing to the opposite direction, now we can move
                r.move();
                //Robot has come to its previous position but pointing to opposite direction (earlier it was this > but now it is this<)
                // We have to make the robot point to the same direction which it was pointing to before moving into the next cell because after this we
                // have to move to another direction
                r.turnRight();
                r.turnRight();
                // this will make robot point to the same direction as before.
            }
            // this completes exploring one branch of the current cell. We have explored all the cell pointing to current direction
            // now we explore to the next direction, so change the direction
            direction = (direction + 90) % 360; // this just changes the variable. We have to manually rotate the robot
            r.turnRight(); // start exploring
        }
    }

}