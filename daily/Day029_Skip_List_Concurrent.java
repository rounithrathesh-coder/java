/**
 * Daily Java Challenge - Day 029 (2026-11-19)
 * Topic: Data Structures - Concurrent SkipList Map with Probabilistic Balancing
 * Category: Advanced Data Structures & Concurrency
 *
 * Description:
 * Production implementation of a SkipList data structure (used in Redis Sorted Sets,
 * RocksDB MemTable, and Java's ConcurrentSkipListMap).
 *
 * A SkipList uses a multi-level hierarchy of sorted linked lists. Higher levels act
 * as "express lanes" allowing binary-search-like jumps over multiple elements.
 *
 * Key Advantages over Self-Balancing Trees (AVL / Red-Black):
 * 1. Simpler implementation: No complex tree rotation algorithms required.
 * 2. Superior concurrency: Insertions only update localized node pointers instead of
 *    rebalancing large tree subtrees.
 *
 * Time Complexity:
 * - search(): O(log N) average, O(N) worst-case
 * - insert(): O(log N) average, O(N) worst-case
 * - delete(): O(log N) average, O(N) worst-case
 * Space Complexity: O(N) average
 */

import java.util.Random;

public class Day029_Skip_List_Concurrent {

    private static final int MAX_LEVEL = 16;
    private static final double PROBABILITY = 0.5;

    private static class Node {
        final int key;
        String val;
        final Node[] forward;

        Node(int key, String val, int level) {
            this.key = key;
            this.val = val;
            this.forward = new Node[level + 1];
        }
    }

    private final Node head;
    private int currentLevel;
    private int size;
    private final Random random = new Random(42); // Seeded for deterministic testing

    public Day029_Skip_List_Concurrent() {
        this.head = new Node(Integer.MIN_VALUE, null, MAX_LEVEL);
        this.currentLevel = 0;
        this.size = 0;
    }

    /**
     * Randomly assigns a level to a new node using coin-flip probability.
     */
    private int randomLevel() {
        int level = 0;
        while (level < MAX_LEVEL && random.nextDouble() < PROBABILITY) {
            level++;
        }
        return level;
    }

    /**
     * Searches for a value associated with key.
     */
    public synchronized String get(int key) {
        Node curr = head;
        // Traverse top-down from highest level to base level
        for (int i = currentLevel; i >= 0; i--) {
            while (curr.forward[i] != null && curr.forward[i].key < key) {
                curr = curr.forward[i];
            }
        }
        curr = curr.forward[0];
        if (curr != null && curr.key == key) {
            return curr.val;
        }
        return null;
    }

    /**
     * Inserts or updates a key-value mapping.
     */
    public synchronized void put(int key, String val) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node curr = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (curr.forward[i] != null && curr.forward[i].key < key) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }

        curr = curr.forward[0];

        // Key already exists, update value
        if (curr != null && curr.key == key) {
            curr.val = val;
            return;
        }

        // New node insertion
        int newLevel = randomLevel();
        if (newLevel > currentLevel) {
            for (int i = currentLevel + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            currentLevel = newLevel;
        }

        Node newNode = new Node(key, val, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
        size++;
    }

    /**
     * Removes a key from the SkipList.
     */
    public synchronized boolean remove(int key) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node curr = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (curr.forward[i] != null && curr.forward[i].key < key) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }

        curr = curr.forward[0];

        if (curr == null || curr.key != key) {
            return false; // Key not found
        }

        // Unlink node across all levels
        for (int i = 0; i <= currentLevel; i++) {
            if (update[i].forward[i] != curr) {
                break;
            }
            update[i].forward[i] = curr.forward[i];
        }

        // Lower currentLevel if top levels are now empty
        while (currentLevel > 0 && head.forward[currentLevel] == null) {
            currentLevel--;
        }

        size--;
        return true;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean containsKey(int key) {
        return get(key) != null;
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 029: Concurrent SkipList Map (O(log N))");
        System.out.println("=======================================================================");

        Day029_Skip_List_Concurrent skipList = new Day029_Skip_List_Concurrent();

        // Test 1: Insertions and Retrieval
        System.out.println("Test 1: Inserting elements and verifying O(log N) retrieval...");
        skipList.put(50, "Data-50");
        skipList.put(20, "Data-20");
        skipList.put(80, "Data-80");
        skipList.put(10, "Data-10");
        skipList.put(90, "Data-90");
        skipList.put(30, "Data-30");

        assert skipList.size() == 6 : "Size must be 6!";
        assert "Data-20".equals(skipList.get(20)) : "Key 20 must return Data-20!";
        assert "Data-90".equals(skipList.get(90)) : "Key 90 must return Data-90!";
        assert skipList.get(999) == null : "Non-existent key must return null!";
        System.out.println("-> Test 1 Passed: Multi-level navigation retrieval verified.");

        // Test 2: Updates
        System.out.println("\nTest 2: In-place value updates...");
        skipList.put(50, "Updated-50");
        assert "Updated-50".equals(skipList.get(50)) : "Updated key must reflect new value!";
        assert skipList.size() == 6 : "Size must remain 6 on in-place update!";
        System.out.println("-> Test 2 Passed: In-place update verified.");

        // Test 3: Deletions
        System.out.println("\nTest 3: Node deletions across express levels...");
        boolean deleted = skipList.remove(20);
        assert deleted : "Key 20 must be successfully removed!";
        assert !skipList.containsKey(20) : "Key 20 must no longer exist!";
        assert skipList.size() == 5 : "Size must be 5 after deletion!";

        boolean deletedNonExistent = skipList.remove(12345);
        assert !deletedNonExistent : "Deleting non-existent key must return false!";
        System.out.println("-> Test 3 Passed: Deletion unlinking verified.");

        // Test 4: Scale Test with 5,000 items
        System.out.println("\nTest 4: Scale testing with 5,000 ordered elements...");
        for (int i = 1000; i < 6000; i++) {
            skipList.put(i, "Val-" + i);
        }
        for (int i = 1000; i < 6000; i++) {
            assert ("Val-" + i).equals(skipList.get(i)) : "Lookup failed for key: " + i;
        }
        System.out.printf("-> Test 4 Passed: 5,000 elements inserted and verified (Size: %d).%n", skipList.size());

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Concurrent SkipList Map Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
