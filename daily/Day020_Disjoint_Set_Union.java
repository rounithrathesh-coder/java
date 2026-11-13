/**
 * Daily Java Challenge - Day 020 (2026-11-13)
 * Topic: Data Structures - Disjoint Set Union (DSU) with Path Compression & Union by Rank
 * Category: Graph Theory & Advanced Data Structures
 *
 * Description:
 * Implementation of Disjoint Set Union (DSU) / Union-Find data structure optimized with:
 * 1. Path Compression in find() - flattens the tree structure on traversal.
 * 2. Union by Rank in union() - attaches smaller depth trees under roots of larger depth trees.
 *
 * Applications:
 * - Dynamic connectivity in networks.
 * - Cycle detection in undirected graphs.
 * - Kruskal's Minimum Spanning Tree (MST) algorithm.
 * - Connected component counting.
 *
 * Time Complexity: O(alpha(N)) nearly O(1) amortized time per operation (Inverse Ackermann function).
 * Space Complexity: O(N) for parent and rank arrays.
 */

import java.util.Arrays;

public class Day020_Disjoint_Set_Union {

    private final int[] parent;
    private final int[] rank;
    private int componentCount;

    /**
     * Initializes DSU with N disjoint sets (0 to N-1).
     *
     * @param n number of elements
     */
    public Day020_Disjoint_Set_Union(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of elements must be greater than zero.");
        }
        this.parent = new int[n];
        this.rank = new int[n];
        this.componentCount = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i; // Every element is its own parent initially
            rank[i] = 0;
        }
    }

    /**
     * Finds representative root of element i with two-pass Path Compression.
     *
     * @param i element index
     * @return representative root of set
     */
    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    /**
     * Unites sets containing element x and element y using Union by Rank.
     *
     * @param x first element
     * @param y second element
     * @return true if two previously disconnected sets were merged; false if they were already in the same set (cycle)
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Already in the same connected component
        }

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        componentCount--;
        return true;
    }

    /**
     * Checks if two elements belong to the same connected component.
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getComponentCount() {
        return componentCount;
    }

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("Daily Java Challenge - Day 020: Disjoint Set Union (DSU)");
        System.out.println("==========================================================");

        // 8 nodes (0 through 7)
        int n = 8;
        Day020_Disjoint_Set_Union dsu = new Day020_Disjoint_Set_Union(n);

        System.out.printf("Initialized DSU with %d isolated components.%n", dsu.getComponentCount());
        assert dsu.getComponentCount() == 8 : "Initial component count must be 8!";

        // Test 1: Connect components into clusters
        // Cluster 1: 0 - 1 - 2
        dsu.union(0, 1);
        dsu.union(1, 2);

        // Cluster 2: 3 - 4
        dsu.union(3, 4);

        // Cluster 3: 5 - 6 - 7
        dsu.union(5, 6);
        dsu.union(6, 7);

        System.out.printf("After adding edges, component count: %d%n", dsu.getComponentCount());
        assert dsu.getComponentCount() == 3 : "Should have exactly 3 connected components!";
        System.out.println("-> Test 1 Passed: Cluster unions and component tracking verified.");

        // Test 2: Connectivity verification
        assert dsu.isConnected(0, 2) : "0 and 2 must be connected!";
        assert dsu.isConnected(5, 7) : "5 and 7 must be connected!";
        assert !dsu.isConnected(0, 3) : "0 and 3 must NOT be connected!";
        assert !dsu.isConnected(2, 6) : "2 and 6 must NOT be connected!";
        System.out.println("-> Test 2 Passed: isConnected() query results verified.");

        // Test 3: Cycle detection
        // 0 and 2 are already in the same cluster. Attempting to add edge (0, 2) creates a redundant cycle.
        boolean redundantEdge = dsu.union(0, 2);
        System.out.printf("Attempting redundant union (0, 2) -> Merged: %b (Cycle detected)%n", redundantEdge);
        assert !redundantEdge : "Union of nodes in the same component must return false!";
        System.out.println("-> Test 3 Passed: Undirected cycle detection verified.");

        // Test 4: Merge remaining clusters into single spanning tree
        dsu.union(2, 3); // Merges Cluster 1 & 2
        dsu.union(4, 5); // Merges Cluster 2 & 3
        System.out.printf("After bridging all clusters, component count: %d%n", dsu.getComponentCount());
        assert dsu.getComponentCount() == 1 : "All nodes should belong to 1 single connected component!";
        assert dsu.isConnected(0, 7) : "Node 0 and Node 7 must now be transitively connected!";
        System.out.println("-> Test 4 Passed: Global spanning tree unified into 1 component.");

        System.out.println("\n==========================================================");
        System.out.println("ALL 4 ASSERTIONS PASSED! Disjoint Set Union (DSU) Verified.");
        System.out.println("==========================================================");
    }
}
