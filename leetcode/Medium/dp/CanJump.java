import java.util.*;

// 55 https://leetcode.com/problems/jump-game/
public class CanJump {
    public static void main(String[] args) {
        CanJump obj = new CanJump();
        int[] arr = {2, 3, 1, 1, 4};

        System.out.println(obj.canJumpTopDown(arr));
    }

    public boolean canJump(int[] nums) {
        return canJumFromPosition(nums, 0);
    }

    private boolean canJumFromPosition(int[] nums, int position) {
        if (position == nums.length - 1) {
            return true;
        }

        int farthestJump = Math.min(position + nums[position], nums.length - 1);


        for (int nextPosition = position + 1; nextPosition <= farthestJump; nextPosition++) {
            if (canJumFromPosition(nums, nextPosition)) {
                return true;
            }
        }

        return false;
    }

    private boolean canJumpTopDown(int[] jumps) {
        Status[] status = new Status[jumps.length];
        return helper(jumps, 0, status);
    }

    private boolean helper(int[] jumps, int position, Status[] status) {
        if (position == jumps.length - 1) {
            return true;
        }

        if (status[position] != Status.UNKNOWN) {
            return status[position] == Status.GOOD;
        }

        int farthestJump = Math.min(jumps[position] + position, jumps.length);

        for (int nextPosition = position + 1; nextPosition <= farthestJump; nextPosition++) {
            if (helper(jumps, nextPosition, status)) {
                status[position] = Status.GOOD;
                return true;
            }
        }

        status[position] = Status.BAD;
        return false;
    }

    private boolean canJumpBottomUp(int[] nums) {
        Status[] status = new Status[nums.length];

        Arrays.fill(status, Status.UNKNOWN);

        status[nums.length] = Status.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int farthestIndex = Math.min(i + nums[i], nums.length - 1);

            for (int j = i + 1; j <= farthestIndex; j++) {
                if (status[j] == Status.GOOD) {
                    status[i] = Status.GOOD;
                    break;
                }
            }

        }


        return status[0] == Status.GOOD;
    }


    enum Status {
        GOOD,
        BAD,
        UNKNOWN
    }
}
