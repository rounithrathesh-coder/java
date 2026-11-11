/**
 * Daily Java Challenge - Day 016 (2026-11-11)
 * Topic: Graph Algorithms - Dijkstra's Single-Source Shortest Path
 * Category: Graph Algorithms / Greedy
 * 
 * Problem Description:
 * Given a weighted, directed/undirected graph with non-negative edge weights and a source vertex,
 * find the shortest path and minimum distance from the source to all other vertices.
 * 
 * Algorithm:
 * - Uses a Min-Heap (PriorityQueue) to greedily extract the unvisited vertex with the minimum distance.
 * - Relaxes adjacent edges: if dist[u] + weight(u, v) < dist[v], update dist[v] and push to heap.
 * - Time Complexity: O((V + E) log V) where V is vertices and E is edges.
 * - Space Complexity: O(V + E) for the adjacency list and distance array.
 */

import java.util.*;

public class Day016_Dijkstra_Shortest_Path {

    public static class Edge {
        int target;
        int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static class NodeDistance implements Comparable<NodeDistance> {
        int node;
        int distance;

        public NodeDistance(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static class Graph {
        private final int vertices;
        private final List<List<Edge>> adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int target, int weight, boolean bidirectional) {
            adjList.get(source).add(new Edge(target, weight));
            if (bidirectional) {
                adjList.get(target).add(new Edge(source, weight));
            }
        }

        public int[] dijkstra(int startNode, int[] parent) {
            int[] distances = new int[vertices];
            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);

            PriorityQueue<NodeDistance> minHeap = new PriorityQueue<>();

            distances[startNode] = 0;
            minHeap.offer(new NodeDistance(startNode, 0));

            boolean[] visited = new boolean[vertices];

            while (!minHeap.isEmpty()) {
                NodeDistance current = minHeap.poll();
                int u = current.node;

                if (visited[u]) continue;
                visited[u] = true;

                for (Edge edge : adjList.get(u)) {
                    int v = edge.target;
                    int weight = edge.weight;

                    // Edge relaxation
                    if (!visited[v] && distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                        parent[v] = u;
                        minHeap.offer(new NodeDistance(v, distances[v]));
                    }
                }
            }

            return distances;
        }

        public List<Integer> reconstructPath(int target, int[] parent) {
            List<Integer> path = new ArrayList<>();
            for (int at = target; at != -1; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);
            return path;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Dijkstra's Shortest Path Algorithm ===");

        int vertices = 6;
        Graph graph = new Graph(vertices);

        // Build sample weighted graph
        graph.addEdge(0, 1, 4, true);
        graph.addEdge(0, 2, 2, true);
        graph.addEdge(1, 2, 1, true);
        graph.addEdge(1, 3, 5, true);
        graph.addEdge(2, 3, 8, true);
        graph.addEdge(2, 4, 10, true);
        graph.addEdge(3, 4, 2, true);
        graph.addEdge(3, 5, 6, true);
        graph.addEdge(4, 5, 3, true);

        int startNode = 0;
        int[] parent = new int[vertices];
        int[] distances = graph.dijkstra(startNode, parent);

        System.out.println("Shortest distances from source node " + startNode + ":");
        for (int i = 0; i < vertices; i++) {
            List<Integer> path = graph.reconstructPath(i, parent);
            System.out.printf("  To Node %d: Distance = %d | Path = %s%n", i, distances[i], path);
        }

        // Assertions
        assert distances[0] == 0 : "Distance to 0 should be 0";
        assert distances[1] == 3 : "Shortest distance to 1 is 3 (0->2->1)";
        assert distances[2] == 2 : "Shortest distance to 2 is 2 (0->2)";
        assert distances[3] == 8 : "Shortest distance to 3 is 8 (0->2->1->3)";
        assert distances[4] == 10 : "Shortest distance to 4 is 10 (0->2->1->3->4)";
        assert distances[5] == 13 : "Shortest distance to 5 is 13 (0->2->1->3->4->5)";

        System.out.println("All Dijkstra test cases passed successfully!");
    }
}
