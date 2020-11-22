import input.TwoDArrayReader;
import java.util.Arrays;

// 1665 https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
public class MinimumEffort {

    public static void main(String[] args) {
        MinimumEffort obj = new MinimumEffort();
        int[][] tasks = new TwoDArrayReader().get2DArray();
        System.out.println(obj.minimumEffort(tasks));
    }

    public int minimumEffort(int[][] tasks) {
        // we can rephrase the question to what is the minimum amount of energy that we need to borrow to complete
        // all the tasks.

        // If we need to complete a task and dont have enough energy than we need to borrow the required energy.
        // We need to minimize this borrowed energy to get the answer.

        // The way we can minimize the borrowed energy is by performing those tasks first which spend less energy to complete
        // or where the difference between required and spent is more

        Arrays.sort(tasks, (task1, task2) -> {
            int remainingEnergyAfterTask1 = task1[1] - task1[0];
            int remainingEnergyAfterTask2 = task2[1] - task2[0];


            // sorting on the basis of which task has more required - spent difference
            // because this will give us maximum energy to do the remaining task
            // and hence we will require min energy overall
            return remainingEnergyAfterTask2 - remainingEnergyAfterTask1;
        });

        int currentEnergyAmount = 0; // initially the energy amount is 0.
        int answer = 0;
        for (int[] task : tasks) {
            if (task[1] > currentEnergyAmount) { // we dont have enough energy to complete this task
                int energyBorrowed = task[1] - currentEnergyAmount;
                answer += energyBorrowed;
                currentEnergyAmount += energyBorrowed; // add the borrowed energy to our current energy
            }

            // we have enough energy to complete the task
            currentEnergyAmount -= task[0]; // completing the task will require some energy.
        }

        return answer;
    }

}