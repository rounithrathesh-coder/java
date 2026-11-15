/**
 * Daily Java Challenge - Day 023 (2026-11-15)
 * Topic: Trees & Range Queries - Segment Tree with Point Updates
 * Category: Data Structures & Divide-and-Conquer Trees
 *
 * Description:
 * Production implementation of an array-backed Segment Tree supporting both
 * Range Sum Queries (RSQ) and Range Minimum Queries (RMQ) with dynamic Point Updates.
 *
 * In a static array, prefix sums allow O(1) range queries, but updates cost O(N).
 * Conversely, updating an array directly costs O(1), but range queries cost O(N).
 * A Segment Tree balances both operations to O(log N) logarithmic time:
 * - Build: O(N)
 * - Point Update: O(log N)
 * - Range Query: O(log N)
 * Space Complexity: O(4 * N) array storage
 */

import java.util.Arrays;

public class Day023_Segment_Tree_Range_Query {

    private final int n;
    private final long[] treeSum;
    private final int[] treeMin;

    /**
     * Constructs and initializes the segment tree for array arr.
     *
     * @param arr source array
     */
    public Day023_Segment_Tree_Range_Query(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        this.n = arr.length;
        // Upper bound for segment tree size is 4 * N
        this.treeSum = new long[4 * n];
        this.treeMin = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }

    /**
     * Recursively builds segment tree nodes.
     * node: 1-based index in tree array
     * [start, end]: range covered by this segment
     */
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            treeSum[node] = arr[start];
            treeMin[node] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        build(arr, leftChild, start, mid);
        build(arr, rightChild, mid + 1, end);

        // Merge children values
        treeSum[node] = treeSum[leftChild] + treeSum[rightChild];
        treeMin[node] = Math.min(treeMin[leftChild], treeMin[rightChild]);
    }

    /**
     * Updates value at index idx to val in O(log N) time.
     *
     * @param idx 0-based array index
     * @param val new value
     */
    public void update(int idx, int val) {
        if (idx < 0 || idx >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx);
        }
        update(1, 0, n - 1, idx, val);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            treeSum[node] = val;
            treeMin[node] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, val);
        } else {
            update(rightChild, mid + 1, end, idx, val);
        }

        treeSum[node] = treeSum[leftChild] + treeSum[rightChild];
        treeMin[node] = Math.min(treeMin[leftChild], treeMin[rightChild]);
    }

    /**
     * Queries the sum of elements in range [l, r] in O(log N) time.
     *
     * @param l inclusive lower bound (0-based)
     * @param r inclusive upper bound (0-based)
     * @return sum of elements in arr[l..r]
     */
    public long querySum(int l, int r) {
        validateRange(l, r);
        return querySum(1, 0, n - 1, l, r);
    }

    private long querySum(int node, int start, int end, int l, int r) {
        // Case 1: Total overlap - segment is completely inside query range
        if (l <= start && end <= r) {
            return treeSum[node];
        }

        // Case 2: No overlap - segment is outside query range
        if (end < l || start > r) {
            return 0;
        }

        // Case 3: Partial overlap - query both children
        int mid = start + (end - start) / 2;
        long leftSum = querySum(2 * node, start, mid, l, r);
        long rightSum = querySum(2 * node + 1, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    /**
     * Queries the minimum element in range [l, r] in O(log N) time.
     *
     * @param l inclusive lower bound (0-based)
     * @param r inclusive upper bound (0-based)
     * @return minimum value in arr[l..r]
     */
    public int queryMin(int l, int r) {
        validateRange(l, r);
        return queryMin(1, 0, n - 1, l, r);
    }

    private int queryMin(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return treeMin[node];
        }
        if (end < l || start > r) {
            return Integer.MAX_VALUE;
        }

        int mid = start + (end - start) / 2;
        int leftMin = queryMin(2 * node, start, mid, l, r);
        int rightMin = queryMin(2 * node + 1, mid + 1, end, l, r);
        return Math.min(leftMin, rightMin);
    }

    private void validateRange(int l, int r) {
        if (l < 0 || r >= n || l > r) {
            throw new IllegalArgumentException(String.format("Invalid range [%d, %d] for array size %d", l, r, n));
        }
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 023: Segment Tree with Range Sum & Min");
        System.out.println("=======================================================================");

        int[] data = {1, 3, 5, 7, 9, 11, 2, 4};
        System.out.println("Source Array: " + Arrays.toString(data));

        Day023_Segment_Tree_Range_Query segTree = new Day023_Segment_Tree_Range_Query(data);

        // Test 1: Range Sum Queries
        System.out.println("\nTest 1: Range Sum Queries...");
        long sum0_7 = segTree.querySum(0, 7);
        long sum1_3 = segTree.querySum(1, 3); // 3 + 5 + 7 = 15
        long sum4_6 = segTree.querySum(4, 6); // 9 + 11 + 2 = 22

        System.out.printf("  Sum[0..7]: %d (Expected: 42)%n", sum0_7);
        System.out.printf("  Sum[1..3]: %d (Expected: 15)%n", sum1_3);
        System.out.printf("  Sum[4..6]: %d (Expected: 22)%n", sum4_6);

        assert sum0_7 == 42 : "Total sum must be 42!";
        assert sum1_3 == 15 : "Sum[1..3] must be 15!";
        assert sum4_6 == 22 : "Sum[4..6] must be 22!";
        System.out.println("-> Test 1 Passed: Range sum queries verified.");

        // Test 2: Range Minimum Queries
        System.out.println("\nTest 2: Range Minimum Queries...");
        int min0_5 = segTree.queryMin(0, 5); // min(1, 3, 5, 7, 9, 11) = 1
        int min3_6 = segTree.queryMin(3, 6); // min(7, 9, 11, 2) = 2
        int min4_5 = segTree.queryMin(4, 5); // min(9, 11) = 9

        System.out.printf("  Min[0..5]: %d (Expected: 1)%n", min0_5);
        System.out.printf("  Min[3..6]: %d (Expected: 2)%n", min3_6);
        System.out.printf("  Min[4..5]: %d (Expected: 9)%n", min4_5);

        assert min0_5 == 1 : "Min[0..5] must be 1!";
        assert min3_6 == 2 : "Min[3..6] must be 2!";
        assert min4_5 == 9 : "Min[4..5] must be 9!";
        System.out.println("-> Test 2 Passed: Range minimum queries verified.");

        // Test 3: Point Update (Update index 2 from 5 to 20)
        System.out.println("\nTest 3: Dynamic Point Update (arr[2] = 20)...");
        segTree.update(2, 20); // Array is now: [1, 3, 20, 7, 9, 11, 2, 4]

        long updatedSum1_3 = segTree.querySum(1, 3); // 3 + 20 + 7 = 30
        long updatedTotalSum = segTree.querySum(0, 7); // 42 - 5 + 20 = 57
        int updatedMin1_3 = segTree.queryMin(1, 3); // min(3, 20, 7) = 3

        System.out.printf("  Post-update Sum[1..3]: %d (Expected: 30)%n", updatedSum1_3);
        System.out.printf("  Post-update Sum[0..7]: %d (Expected: 57)%n", updatedTotalSum);
        System.out.printf("  Post-update Min[1..3]: %d (Expected: 3)%n", updatedMin1_3);

        assert updatedSum1_3 == 30 : "Updated sum must reflect new value!";
        assert updatedTotalSum == 57 : "Total sum must reflect new value!";
        assert updatedMin1_3 == 3 : "Min query must recalculate correctly!";
        System.out.println("-> Test 3 Passed: Logarithmic point update propagated accurately.");

        // Test 4: Single element boundary query
        assert segTree.querySum(2, 2) == 20 : "Single element sum must equal value!";
        assert segTree.queryMin(2, 2) == 20 : "Single element min must equal value!";
        System.out.println("\nTest 4 Passed: Single-element range boundary verified.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Segment Tree Verified & Operational (O(log N)).");
        System.out.println("=======================================================================");
    }
}
