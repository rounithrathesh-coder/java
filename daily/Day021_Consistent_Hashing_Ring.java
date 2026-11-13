/**
 * Daily Java Challenge - Day 021 (2026-11-13)
 * Topic: Distributed Systems - Consistent Hashing Ring with Virtual Nodes
 * Category: Distributed Systems & Hash Rings
 *
 * Description:
 * Production implementation of Consistent Hashing with virtual nodes (as used in
 * Apache Cassandra, DynamoDB, Discord, and Memcached).
 *
 * In standard modulo hashing (hash(key) % N), adding or removing a node causes
 * nearly 100% of keys to be remapped to different servers (cache stampede).
 * With Consistent Hashing:
 * 1. Nodes and keys are mapped onto a 64-bit circular ring.
 * 2. Each key is assigned to the first node encountered clockwise on the ring (tailMap).
 * 3. Adding or removing a node only remaps K / N keys on average.
 * 4. Virtual nodes ensure uniform statistical key distribution and eliminate hot-spots.
 *
 * Time Complexity:
 * - getNode(): O(log(N * V)) where N is physical nodes and V is virtual replicas
 * - addNode(): O(V * log(N * V))
 * - removeNode(): O(V * log(N * V))
 * Space Complexity: O(N * V) in TreeMap
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Day021_Consistent_Hashing_Ring<T> {

    private final int numberOfReplicas;
    private final SortedMap<Long, T> ring = new TreeMap<>();
    private final MessageDigest md;

    public Day021_Consistent_Hashing_Ring(int numberOfReplicas, Collection<T> nodes) {
        if (numberOfReplicas <= 0) {
            throw new IllegalArgumentException("Number of replicas must be positive.");
        }
        this.numberOfReplicas = numberOfReplicas;
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 digest algorithm not available", e);
        }

        if (nodes != null) {
            for (T node : nodes) {
                addNode(node);
            }
        }
    }

    /**
     * Adds a physical node to the ring, generating virtual replicas.
     */
    public synchronized void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(node.toString() + "#VN_" + i);
            ring.put(hash, node);
        }
    }

    /**
     * Removes a physical node and all its virtual replicas from the ring.
     */
    public synchronized void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(node.toString() + "#VN_" + i);
            ring.remove(hash);
        }
    }

    /**
     * Resolves which physical node is responsible for a given key.
     */
    public synchronized T getNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }

        long hash = hash(key);
        // Find clockwise successor on the ring
        if (!ring.containsKey(hash)) {
            SortedMap<Long, T> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }

        return ring.get(hash);
    }

    /**
     * 64-bit hash from MD5 digest for uniform circular distribution.
     */
    private long hash(String key) {
        md.reset();
        byte[] digest = md.digest(key.getBytes(StandardCharsets.UTF_8));
        long h = 0;
        for (int i = 0; i < 8; i++) {
            h = (h << 8) | (digest[i] & 0xFF);
        }
        return h;
    }

    public synchronized int getRingSize() {
        return ring.size();
    }

    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("Daily Java Challenge - Day 021: Consistent Hashing with Virtual Nodes");
        System.out.println("===================================================================");

        List<String> initialNodes = Arrays.asList("cache-node-1", "cache-node-2", "cache-node-3");
        int replicas = 100; // 100 virtual nodes per physical node
        Day021_Consistent_Hashing_Ring<String> hashRing = new Day021_Consistent_Hashing_Ring<>(replicas, initialNodes);

        System.out.printf("Initialized ring with %d physical nodes (%d virtual nodes total).%n",
                initialNodes.size(), hashRing.getRingSize());
        assert hashRing.getRingSize() == 300 : "Ring size must be 300 (3 nodes * 100 replicas)!";

        // Test 1: Deterministic key routing
        String key1 = "user:session:98124";
        String key2 = "user:profile:43011";
        String targetNode1 = hashRing.getNode(key1);
        String targetNode2 = hashRing.getNode(key2);

        System.out.printf("Key '%s' routed to -> %s%n", key1, targetNode1);
        System.out.printf("Key '%s' routed to -> %s%n", key2, targetNode2);
        assert targetNode1 != null && targetNode2 != null : "Target nodes cannot be null!";
        assert targetNode1.equals(hashRing.getNode(key1)) : "Routing must be idempotent and deterministic!";
        System.out.println("-> Test 1 Passed: Deterministic routing verified.");

        // Test 2: Uniform Key Distribution across 10,000 keys
        System.out.println("\nTest 2: Analyzing distribution across 10,000 simulated user keys...");
        Map<String, Integer> distribution = new HashMap<>();
        int sampleSize = 10_000;
        for (int i = 0; i < sampleSize; i++) {
            String testKey = "key_uuid_" + i;
            String node = hashRing.getNode(testKey);
            distribution.put(node, distribution.getOrDefault(node, 0) + 1);
        }

        distribution.forEach((node, count) -> {
            double percentage = (count * 100.0) / sampleSize;
            System.out.printf("  %s -> %d keys (%.2f%%)%n", node, count, percentage);
            assert percentage > 25.0 && percentage < 42.0 : "Distribution must be balanced around ~33.3% per node!";
        });
        System.out.println("-> Test 2 Passed: Virtual nodes achieve uniform load distribution.");

        // Test 3: Minimal key remapping when adding a 4th node (cache-node-4)
        System.out.println("\nTest 3: Adding 4th node (cache-node-4) and measuring key disruption...");
        Map<String, String> initialRouting = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            String testKey = "test_item_" + i;
            initialRouting.put(testKey, hashRing.getNode(testKey));
        }

        hashRing.addNode("cache-node-4");
        assert hashRing.getRingSize() == 400 : "Ring size must expand to 400 virtual nodes!";

        int remappedKeys = 0;
        for (int i = 0; i < 1000; i++) {
            String testKey = "test_item_" + i;
            String newTarget = hashRing.getNode(testKey);
            if (!newTarget.equals(initialRouting.get(testKey))) {
                remappedKeys++;
            }
        }

        double migrationRate = (remappedKeys * 100.0) / 1000;
        System.out.printf("  Total remapped keys: %d / 1000 (%.1f%% remapped)%n", remappedKeys, migrationRate);
        // Theoretical remapping rate for 1/4 nodes added is ~25%.
        assert migrationRate < 35.0 : "Consistent hashing should remap approximately 25% of keys, not 100%!";
        System.out.println("-> Test 3 Passed: Minimal key migration verified (prevents cache stampede).");

        System.out.println("\n===================================================================");
        System.out.println("ALL 3 ASSERTIONS PASSED! Consistent Hashing Ring Verified.");
        System.out.println("===================================================================");
    }
}
