/**
 * Daily Java Challenge - Day 024 (2026-11-15)
 * Topic: System Design - LFU (Least Frequently Used) Cache with Doubly Linked Lists
 * Category: Data Structures & System Design
 *
 * Description:
 * Production implementation of an optimal LFU (Least Frequently Used) Cache
 * achieving O(1) time complexity for both get() and put() operations.
 *
 * Architecture:
 * 1. keyTable: HashMap<Key, Node> for O(1) key-to-node lookup.
 * 2. freqTable: HashMap<Frequency, DoublyLinkedList> grouping nodes by access count.
 * 3. minFreq: Integer tracking the lowest frequency bucket currently in the cache.
 *
 * Eviction Policy:
 * When capacity is exceeded, the least frequently used item is evicted. If multiple
 * items share the same minimum frequency, the least recently used (LRU) among them is evicted.
 *
 * Time Complexity:
 * - get(key): O(1)
 * - put(key, value): O(1)
 * Space Complexity: O(N) where N is cache capacity
 */

import java.util.HashMap;
import java.util.Map;

public class Day024_LRU_LFU_Hybrid_Cache {

    private static class Node {
        final int key;
        int value;
        int freq;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private static class DoublyLinkedList {
        private final Node head;
        private final Node tail;
        private int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size == 0) return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyTable;
    private final Map<Integer, DoublyLinkedList> freqTable;

    public Day024_LRU_LFU_Hybrid_Cache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cache capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyTable = new HashMap<>();
        this.freqTable = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyTable.get(key);
        if (node == null) {
            return -1;
        }
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (keyTable.containsKey(key)) {
            Node node = keyTable.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        // If at capacity, evict the LRU node from minFreq bucket
        if (keyTable.size() >= capacity) {
            DoublyLinkedList minList = freqTable.get(minFreq);
            Node evicted = minList.removeLast();
            keyTable.remove(evicted.key);
        }

        // Insert new node with frequency = 1
        Node newNode = new Node(key, value);
        keyTable.put(key, newNode);
        minFreq = 1;

        DoublyLinkedList list1 = freqTable.computeIfAbsent(1, k -> new DoublyLinkedList());
        list1.addFirst(newNode);
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqTable.get(oldFreq);
        oldList.remove(node);

        // If minFreq bucket is now empty, increment minFreq
        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;
        DoublyLinkedList newList = freqTable.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
        newList.addFirst(node);
    }

    public int size() {
        return keyTable.size();
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 024: O(1) LFU (Least Frequently Used) Cache");
        System.out.println("=======================================================================");

        // Capacity of 3
        Day024_LRU_LFU_Hybrid_Cache lfu = new Day024_LRU_LFU_Hybrid_Cache(3);

        System.out.println("Test 1: Basic insertion and retrieval...");
        lfu.put(1, 100);
        lfu.put(2, 200);
        lfu.put(3, 300);

        assert lfu.get(1) == 100 : "Key 1 must return 100!";
        assert lfu.get(2) == 200 : "Key 2 must return 200!";
        // Key 1 has freq = 2, Key 2 has freq = 2, Key 3 has freq = 1
        System.out.println("-> Test 1 Passed: Get and put verified.");

        System.out.println("\nTest 2: LFU Eviction (Key 3 should be evicted as freq=1)...");
        lfu.put(4, 400); // Key 3 has lowest frequency (1) -> evicted!
        assert lfu.get(3) == -1 : "Key 3 should have been evicted by LFU policy!";
        assert lfu.get(4) == 400 : "Key 4 must be present!";
        assert lfu.get(1) == 100 : "Key 1 must still be present!";
        System.out.println("-> Test 2 Passed: Least frequently used item evicted correctly.");

        System.out.println("\nTest 3: Tie-breaker with LRU eviction among same frequency...");
        // At this point:
        // Key 1: freq = 3 (put, get, get)
        // Key 2: freq = 2 (put, get)
        // Key 4: freq = 2 (put, get)
        // Let's insert Key 5. Tie between Key 2 and Key 4 (both freq=2).
        // Since Key 2 was accessed earlier than Key 4, Key 2 is LRU among tied items -> evict Key 2!
        lfu.put(5, 500);
        assert lfu.get(2) == -1 : "Key 2 must be evicted by LRU tie-breaker!";
        assert lfu.get(4) == 400 : "Key 4 must survive!";
        assert lfu.get(5) == 500 : "Key 5 must be present!";
        System.out.println("-> Test 3 Passed: LRU tie-breaker policy verified.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! O(1) LFU Cache Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
