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
        boolean[] roomsVisited = new boolean[rooms.size()];
        roomsVisited[0] = true;

        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(0);

        while (!queue.isEmpty()) {
            int room = queue.poll();
            roomsVisited[room] = true;

            List<Integer> keys = rooms.get(room);

            for (int key : keys) {
                if(!roomsVisited[key]) {
                    roomsVisited[key] = true;
                    queue.offer(key);
                }
            }
        }


        return hasAllRoomsVisited(roomsVisited);
    }

    private boolean hasAllRoomsVisited(boolean[] roomsVisited) {
        for (boolean room : roomsVisited) {
            if (!room)
                return false;
        }
        return true;
    }
}
