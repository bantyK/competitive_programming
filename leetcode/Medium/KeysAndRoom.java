package solutions.medium;

import java.lang.reflect.Array;
import java.util.*;

//https://leetcode.com/problems/keys-and-rooms/
public class KeysAndRoom {

    public static void main(String[] args) {
        KeysAndRoom obj = new KeysAndRoom();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Collections.singletonList(3));
        rooms.add(new ArrayList<>());

        String result = obj.canVisitAllRooms(rooms) ? "True" : "False";

        System.out.println(result);
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        final int size = rooms.size();
        boolean[] canEnterRoom = new boolean[size];

        dfs(rooms, 0, canEnterRoom);

        for (int i = 0; i < size; i++) {
            if (!canEnterRoom[i]) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int current, boolean[] visited) {
        visited[current] = true;
        for (int room : rooms.get(current)) {
            if (!visited[room]) {
                dfs(rooms, room, visited);
            }
        }
    }
}
