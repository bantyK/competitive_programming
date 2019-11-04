package src;

import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/

/**
 * This is a Hash map and double linked list based implementation which gives constant time for get and put
 * operations.
 * <p>
 * The key of the map is the actual key which was passed in the put method and the value is the reference to the
 * corresponding node in the doubly linked list.
 * <p>
 * New elements are always added at the end of the linked list.
 * If the LRU is at its max capacity remove the head because that's the least recently used value.
 * The node next to head will be the new head.
 * <p>
 * For the get operation, first check if the key exist in the hash map, if exist, then simply return the value of the
 * node which is referenced by the hash map for that key. Else return -1.
 * <p>
 * The Least recently used element will always be at the head of the linked list.
 */

public class LRUCache {
    private final int capacity;
    // Number of items that the cache is holding at any instance
    private int currentSize = 0;
    private Node head = null; // represent the LRU node
    private Node tail;
    private HashMap<Integer, Node> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    // Only for testing
    public int getHeadValue() {
        if (head != null) {
            return head.data;
        }
        return -1;
    }

    // Only for testing
    public int getTailValue() {
        if (tail != null) {
            return tail.data;
        }
        return -1;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);

            // If the element is at the head, then simply return the value, no rearrangements required.
            if (node == tail) {
                return node.data;
            }

            if (node == head) {
                if (head.next != null) {
                    head = head.next;
                }
            } else {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
                tail.next = node;
                node.prev = tail;
                node.next = null;
                tail = node;
            }

            return node.data;
        } else {
            return -1;
        }
    }

    public void insert(int key, int value) {
        if (capacity == 0) return;

        Node node = new Node(key, value);
        cache.put(key, node);
        if (currentSize < capacity) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            currentSize += 1;
        } else {
            if (head == tail) {
                cache.remove(head.key);
                head = tail = node;
            } else {
                cache.remove(head.key);
                tail.next = node;
                node.prev = tail;
                tail = node;

                Node temp = head;
                temp.next.prev = null;
                head = temp.next;
                temp = null; // for garbage collection
            }

        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // update the existing node
            // make it the tail node
            Node temp = cache.get(key);
            if (temp == tail) {
                // if the new node is already the tail node, no need for any re-arrangement, just change the value of the node.
                temp.data = value;
            } else if (temp == head) {
                temp.data = value;
                temp.next.prev = null;
                head = temp.next;
                temp.next = null;
                tail.next = temp;
                temp.prev = tail;
                tail = temp;
            } else {
                // connect the nodes which are in the left and right of the temp node
                temp.data = value;
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                // put the temp node at the last
                tail.next = temp;
                temp.prev = tail;
                tail = temp;
            }
        } else {
            insert(key, value);
        }
    }

    private static class Node {
        int key;
        int data;
        Node prev;
        Node next;

        Node(int key, int data) {
            this.key = key;
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
}
