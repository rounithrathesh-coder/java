/**
 * Daily Java Challenge - Day 028 (2026-11-18)
 * Topic: Probabilistic Data Structures - Bloom Filter with Optimal Hash Sizing
 * Category: Probabilistic Algorithms & Distributed Systems
 *
 * Description:
 * Production implementation of a space-efficient Bloom Filter (used in Google Bigtable,
 * Apache Cassandra, PostgreSQL, and web browser safe-browsing caches).
 *
 * Mathematical Principles:
 * Given expected items 'n' and acceptable false positive rate 'p':
 * 1. Optimal bit array size: m = - (n * ln(p)) / (ln(2)^2)
 * 2. Optimal number of hash functions: k = (m / n) * ln(2)
 * 3. Double-Hashing Optimization (Kirsch-Mitzenmacher):
 *      hash_i(x) = (h1(x) + i * h2(x)) mod m
 *    Generates k independent hash values from just two 64-bit hash computations.
 *
 * Invariants:
 * - Zero False Negatives: If an item was added, mightContain() MUST return true.
 * - Bounded False Positives: If an item was not added, mightContain() returns true with <= p probability.
 *
 * Time Complexity:
 * - add(): O(k) bit operations
 * - mightContain(): O(k) bit operations
 * Space Complexity: O(m) bits
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Day028_Bloom_Filter_Probabilistic {

    private final int bitSize;
    private final int hashFunctionsCount;
    private final BitSet bitSet;
    private int elementCount = 0;

    /**
     * Constructs an optimal Bloom Filter.
     *
     * @param expectedElements expected number of items (n)
     * @param falsePositiveRate targeted false positive probability (p), e.g. 0.01 for 1%
     */
    public Day028_Bloom_Filter_Probabilistic(int expectedElements, double falsePositiveRate) {
        if (expectedElements <= 0) {
            throw new IllegalArgumentException("Expected elements must be greater than zero.");
        }
        if (falsePositiveRate <= 0.0 || falsePositiveRate >= 1.0) {
            throw new IllegalArgumentException("False positive rate must be between 0.0 and 1.0 exclusive.");
        }

        // m = - (n * ln(p)) / (ln(2)^2)
        this.bitSize = (int) Math.ceil(- (expectedElements * Math.log(falsePositiveRate)) / (Math.pow(Math.log(2), 2)));
        // k = (m / n) * ln(2)
        this.hashFunctionsCount = Math.max(1, (int) Math.round(((double) bitSize / expectedElements) * Math.log(2)));
        this.bitSet = new BitSet(bitSize);
    }

    /**
     * Inserts an element into the Bloom filter.
     */
    public void add(String element) {
        if (element == null) return;
        long[] hashes = hash64(element);
        long h1 = hashes[0];
        long h2 = hashes[1];

        for (int i = 0; i < hashFunctionsCount; i++) {
            long combinedHash = h1 + (i * h2);
            int bitIndex = (int) Math.floorMod(combinedHash, bitSize);
            bitSet.set(bitIndex);
        }
        elementCount++;
    }

    /**
     * Tests whether an element is present in the set.
     *
     * @return false if the element is DEFINITELY NOT in the set;
     *         true if the element MIGHT be in the set (with small false-positive probability).
     */
    public boolean mightContain(String element) {
        if (element == null) return false;
        long[] hashes = hash64(element);
        long h1 = hashes[0];
        long h2 = hashes[1];

        for (int i = 0; i < hashFunctionsCount; i++) {
            long combinedHash = h1 + (i * h2);
            int bitIndex = (int) Math.floorMod(combinedHash, bitSize);
            if (!bitSet.get(bitIndex)) {
                return false; // Definitely not present
            }
        }
        return true; // Might be present
    }

    /**
     * Computes two 64-bit hash values using SHA-256 for Kirsch-Mitzenmacher double hashing.
     */
    private long[] hash64(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            long h1 = 0;
            long h2 = 0;
            for (int i = 0; i < 8; i++) {
                h1 = (h1 << 8) | (bytes[i] & 0xFF);
            }
            for (int i = 8; i < 16; i++) {
                h2 = (h2 << 8) | (bytes[i] & 0xFF);
            }
            return new long[]{h1, h2};
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm missing", e);
        }
    }

    public int getBitSize() {
        return bitSize;
    }

    public int getHashFunctionsCount() {
        return hashFunctionsCount;
    }

    public int getElementCount() {
        return elementCount;
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 028: Bloom Filter with Optimal Hash Sizing");
        System.out.println("=======================================================================");

        int expectedItems = 10_000;
        double targetFPR = 0.01; // 1% false positive rate target

        Day028_Bloom_Filter_Probabilistic filter = new Day028_Bloom_Filter_Probabilistic(expectedItems, targetFPR);

        System.out.printf("Initialized Bloom Filter for %d items with target FPR %.2f%%:%n", expectedItems, targetFPR * 100);
        System.out.printf("  Total Bit Array Size (m): %d bits (~%.2f KB)%n", filter.getBitSize(), filter.getBitSize() / 8192.0);
        System.out.printf("  Optimal Hash Functions (k): %d%n", filter.getHashFunctionsCount());

        // Test 1: Insert 10,000 items and verify ZERO false negatives
        System.out.println("\nTest 1: Adding 10,000 elements and verifying zero false negatives...");
        Set<String> insertedElements = new HashSet<>();
        for (int i = 0; i < expectedItems; i++) {
            String key = "usr_token_" + i;
            filter.add(key);
            insertedElements.add(key);
        }

        for (String key : insertedElements) {
            boolean found = filter.mightContain(key);
            assert found : "CRITICAL INVARIANT VIOLATION: Bloom filter returned false negative for: " + key;
        }
        System.out.printf("-> Test 1 Passed: 10,000/10,000 inserted items found (0%% False Negatives).%n");

        // Test 2: Query 20,000 non-existent items to measure empirical False Positive Rate (FPR)
        System.out.println("\nTest 2: Querying 20,000 distinct non-existent items to measure FPR...");
        int falsePositiveCount = 0;
        int nonExistentTests = 20_000;

        for (int i = 0; i < nonExistentTests; i++) {
            String absentKey = "ghost_session_uuid_" + i;
            if (filter.mightContain(absentKey)) {
                falsePositiveCount++;
            }
        }

        double measuredFPR = (falsePositiveCount * 100.0) / nonExistentTests;
        System.out.printf("  False Positives: %d / %d%n", falsePositiveCount, nonExistentTests);
        System.out.printf("  Measured FPR: %.2f%% (Target was %.2f%%)%n", measuredFPR, targetFPR * 100);

        // Measured FPR should be within reasonable bounds of target 1% (e.g. < 2.0%)
        assert measuredFPR < 2.0 : "Empirical false positive rate exceeded allowable bound!";
        System.out.println("-> Test 2 Passed: Measured false positive rate matches theoretical bound.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Scalable Bloom Filter Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
