/**
 * Practice Implementation: Adjacency List Graph Representation with BFS and DFS Traversals
 * Revision: #181 | Date: 2026-10-07 20:33:14
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.*;
public class GraphBFSandDFS {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    public void addEdge(int u, int v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(start); visited.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.getOrDefault(u, Collections.emptyList())) {
                if (!visited.contains(v)) { visited.add(v); q.add(v); }
            }
        }
    }
    public static void main(String[] args) {
        GraphBFSandDFS g = new GraphBFSandDFS();
        g.addEdge(1, 2); g.addEdge(2, 3);
        g.bfs(1);
        System.out.println("Graph traversal completed.");
    }
}
