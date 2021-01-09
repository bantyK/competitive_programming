import java.util.LinkedList;
import java.util.Queue;

// 1700 https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
public class StudentsUnableToEatLunch {
    public static void main(String[] args) {
        StudentsUnableToEatLunch obj = new StudentsUnableToEatLunch();
//        System.out.println(obj.countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
        System.out.println(obj.countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println(obj.countStudents(new int[]{1, 1, 1, 1}, new int[]{0, 0, 0, 0}));
    }

    public int countStudents2(int[] students, int[] sandwiches) {
        Queue<Integer> studentsQueue = new LinkedList<>();
        for (int student : students) {
            studentsQueue.add(student);
        }

        for (int i = 0; i < sandwiches.length; i++) {
            if (studentsQueue.peek() == sandwiches[i]) {
                studentsQueue.poll();
            } else {
                int size = studentsQueue.size();
                int rotations = 0;
                while (studentsQueue.peek() != sandwiches[i]) {
                    studentsQueue.add(studentsQueue.poll());
                    rotations++;
                    if (rotations == size) {
                        return size;
                    }
                }
                --i;
            }
        }
        return 0;
    }

    // Optimised solution
    public int countStudents(int[] students, int[] sandwiches) {
        int studentsWhoNeedType0Sandwich = 0;
        int studentsWhoNeedType1Sandwich = 0;

        for (int student : students) {
            if (student == 0) studentsWhoNeedType0Sandwich++;
            else studentsWhoNeedType1Sandwich++;
        }

        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (studentsWhoNeedType0Sandwich == 0) {
                    // there are no students who need a type 0 sandwich, meaning all the students who wanted
                    // there is no one in the queue who wants the sandwich, all the remaining students will not be able to eat lunch!
                    return studentsWhoNeedType1Sandwich;
                }
                // there is a student who want to eat a type 0 sandwich
                // have him eat the sandwich and decrement the count of that student(remove him from the queue)
                studentsWhoNeedType0Sandwich--;
            } else {
                if (studentsWhoNeedType1Sandwich == 0) {
                    return studentsWhoNeedType0Sandwich;
                }
                studentsWhoNeedType1Sandwich--;
            }
        }
        return 0; // everyone got their lunch
    }

}
