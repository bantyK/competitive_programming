//706 https://leetcode.com/problems/design-hashmap/
class MyHashMap {

    ListNode[] nodes;

    public MyHashMap() {
        nodes = new ListNode[10000];
    }


    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = getHashCode(key);
        ListNode node = new ListNode(key, value);
        if (nodes[hash] == null) {
            // dummy node to avoid NPEs
            nodes[hash] = new ListNode(-1, -1);
        }

        ListNode prev = find(nodes[hash], key);
        if (prev.next == null) {
            prev.next = node;
        } else {
            prev.next.val = value;
        }

    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = getHashCode(key);
        ListNode prev = find(nodes[hash], key);
        if (prev == null || prev.next == null) return -1;
        return prev.next.val;

    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = getHashCode(key);
        ListNode prev = find(nodes[hash], key);
        if (prev == null || prev.next == null) {
            // key does not exist
            return;
        }
        prev.next = prev.next.next;
    }

    private int getHashCode(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode find(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        // we are returning prev because this will help in removing. This way we will only have 1 common method for get and remove
        return prev;
    }

    class ListNode {
        int key;
        int val;
        ListNode next;

        ListNode(int k, int v) {
            key = k;
            val = v;
            next = null;
        }
    }
}