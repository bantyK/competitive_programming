// #1041 https://leetcode.com/problems/robot-bounded-in-circle/
// if the robot ends up in the same position as before, there is a circle
// If the robot ends up in a different position, but faces direction other than north then there will be a cirle
// If at the end, robot faces the north then there wont be a circle, the robot will drift apart
fun isRobotBounded(instructions: String): Boolean {
    // variables which indicates the direction where the robot is pointing
    val NORTH = 0
    val EAST = 1
    val SOUTH = 2
    val WEST = 3

    var currentDirection = NORTH // robot is initially pointing to North
    val currentPosition = intArrayOf(0, 0) // robot is initially placed at origin (row, col)

    for (char in instructions) {
        when (char) {
            'G' -> {
                when (currentDirection) {
                    NORTH -> {
                        currentPosition[0] = currentPosition[0] - 1
                    }
                    EAST -> {
                        currentPosition[1] = currentPosition[1] + 1
                    }
                    SOUTH -> {
                        currentPosition[0] = currentPosition[0] + 1
                    }
                    else -> {
                        currentPosition[1] = currentPosition[1] - 1
                    }
                }
            }
            'L' -> {
                currentDirection = when (currentDirection) {
                    NORTH -> {
                        WEST
                    }
                    EAST -> {
                        NORTH
                    }
                    SOUTH -> {
                        EAST
                    }
                    else -> {
                        SOUTH
                    }
                }
            }
            'R' -> {
                currentDirection = when (currentDirection) {
                    NORTH -> {
                        EAST
                    }
                    EAST -> {
                        SOUTH
                    }
                    SOUTH -> {
                        WEST
                    }
                    else -> {
                        NORTH
                    }
                }
            }
        }
    }

//    println(currentDirection)
//    println(currentPosition.contentToString())

    if (currentPosition[0] == 0 && currentPosition[1] == 0) {
        return true
    }
    return currentDirection != NORTH
}

fun main() {
    /*println(isRobotBounded("GGLLGG"))
    println(isRobotBounded("GGLGG"))
    println(isRobotBounded("GGLGGG"))
    println(isRobotBounded("GGGLGG"))
    println(isRobotBounded("GLGLGGLGL"))*/
    println(isRobotBounded("GLRLLGLL"))

}