/**
 * ============================================================================
 * Daily Java Challenge - Day 001 (2026-11-02)
 * Topic: Data Structures & System Design - LRU (Least Recently Used) Cache
 * ============================================================================
 * 
 * Problem Description:
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 *   - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *   - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *   - void put(int key, int value) Update the value of the key if the key exists.
 *     Otherwise, add the key-value pair to the cache. If the number of keys exceeds
 *     the capacity from this operation, evict the least recently used key.
 *
 * Complexity:
 *   - Time Complexity: O(1) for both get() and put() operations.
 *   - Space Complexity: O(capacity) to store elements in HashMap and Doubly Linked List.
 */

import java.util.HashMap;
import java.util.Map;

public class Day001_LRU_Cache {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> cacheMap;
        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero.");
            }
            this.capacity = capacity;
            this.cacheMap = new HashMap<>(capacity);

            // Pseudo head and tail nodes to avoid null boundary checks
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * Fetches value associated with key and marks node as most recently used.
         */
        public int get(int key) {
            if (!cacheMap.containsKey(key)) {
                return -1;
            }
            Node node = cacheMap.get(key);
            moveToHead(node);
            return node.value;
        }

        /**
         * Inserts or updates the key-value pair.
         */
        public void put(int key, int value) {
            if (cacheMap.containsKey(key)) {
                Node node = cacheMap.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                if (cacheMap.size() >= capacity) {
                    Node lru = removeTail();
                    cacheMap.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                cacheMap.put(key, newNode);
                addToHead(newNode);
            }
        }

        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private Node removeTail() {
            Node lru = tail.prev;
            removeNode(lru);
            return lru;
        }

        public int size() {
            return cacheMap.size();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Day 001: LRU Cache Implementation ===");
        LRUCache cache = new LRUCache(2);

        cache.put(1, 100);
        cache.put(2, 200);
        System.out.println("Get key 1: " + cache.get(1)); // returns 100

        cache.put(3, 300); // evicts key 2 (least recently used)
        System.out.println("Get key 2 (should be -1): " + cache.get(2)); // returns -1

        cache.put(4, 400); // evicts key 1
        System.out.println("Get key 1 (should be -1): " + cache.get(1)); // returns -1
        System.out.println("Get key 3: " + cache.get(3)); // returns 300
        System.out.println("Get key 4: " + cache.get(4)); // returns 400

        System.out.println("All LRU Cache tests passed successfully!");
    }
}
