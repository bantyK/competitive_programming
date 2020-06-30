package dfs;

import java.util.*;

//690 https://leetcode.com/problems/employee-importance/
public class EmployeeImportance {
    public static void main(String[] args) {
        EmployeeImportance obj = new EmployeeImportance();
    }

    //bfs
    public int getImportanceBFS(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            int empId = employee.id;
            map.put(empId, employee);
        }

        int totalImportance = 0;

        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));

        while (!queue.isEmpty()) {
            Employee current = queue.poll();

            totalImportance += current.importance;

            for (int sub : current.subordinates) {
                queue.offer(map.get(sub));
            }
        }

        return totalImportance;
    }

    //dfs
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();

        for (Employee employee : employees) {
            int empId = employee.id;
            map.put(empId, employee);
        }

        return dfs(map, id);
    }

    private int dfs(Map<Integer, Employee> map, int id) {
        Employee employee = map.get(id);
        int importance = employee.importance;
        for (int emp : employee.subordinates) {
            importance += dfs(map, emp);
        }
        return importance;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
