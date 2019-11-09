package src;

import java.util.*;

//https://leetcode.com/problems/reorganize-string/
public class ReorganiseString {
    public static void main(String[] args) {
        ReorganiseString obj = new ReorganiseString();

        //obj.reorganizeString("vvvlo") == vlvov
//        obj.reorganizeString("aab")
        System.out.println(
                obj.reorganizeString("baab")
        );
    }

    private static class HeapNode {
        final char c;
        int count;


        private HeapNode(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }


    public String reorganizeString(String s) {

        Map<Character, Integer> countMap = new HashMap<>();

        PriorityQueue<HeapNode> maxHeap = new PriorityQueue<>((h1, h2) -> h2.count - h1.count);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (Character key : countMap.keySet()) {
            maxHeap.add(new HeapNode(key, countMap.get(key)));
        }

        List<Character> chars;
        int n;
        while (!maxHeap.isEmpty()) {
            chars = new ArrayList<>();
            n = 0;

            for (int i = 0; i < 2; i++) {
                if (!maxHeap.isEmpty())
                    chars.add(maxHeap.poll().c);
            }

            for (Character c : chars) {
                builder.append(c);
                int count = countMap.get(c);
                if (--count > 0) {
                    countMap.put(c, count);
                    maxHeap.add(new HeapNode(c, count));
                } else {
                    countMap.remove(c);
                }
            }

            if (maxHeap.size() == 1 && maxHeap.peek().count > 1) return "";
        }

        return builder.toString();
    }
}