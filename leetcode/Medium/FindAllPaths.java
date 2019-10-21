package solutions.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindAllPaths {
    public static void main(String[] args) {
        FindAllPaths obj = new FindAllPaths();
//        int[][] graph = {{1, 2}, {3}, {3}, {}};
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};

        List<List<Integer>> result = new ArrayList<>();
        obj.printAllPaths(graph, result);

        for (List<Integer> list : result) {
            System.out.println(list);
        }

    }

    public void printAllPaths(int[][] graph, List<List<Integer>> result) {
        boolean[] isVisited = new boolean[graph.length];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(0);
        //Call recursive utility
        printAllPathsUtil(graph, 0, graph.length - 1, isVisited, pathList, result);
    }


    private void printAllPathsUtil(int[][] graph, Integer src, Integer dest, boolean[] isVisited, List<Integer> localPathList, List<List<Integer>> result) {

        // Mark the current node
        isVisited[src] = true;

        if (src.equals(dest)) {
            System.out.println("Path : " + localPathList);
            result.add(new ArrayList<>(localPathList));
            // We need to visit this node again, so making it false.
            isVisited[src] = false;
            // if match found then no need to traverse more till depth
            return;
        }

        for (Integer i : graph[src]) {
            if (!isVisited[i]) {
                // store current node in path []
                localPathList.add(i);
                printAllPathsUtil(graph, i, dest, isVisited, localPathList, result);

                // remove current node in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[src] = false;
    }

}
