/**
 * Practice Implementation: Single-Source Shortest Path using Min-Priority Queue
 * Revision: #37 | Date: 2026-07-14 14:21:12
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.*;
public class DijkstraShortestPath {
    static class Edge { int to, weight; Edge(int t, int w) { to = t; weight = w; } }
    public static int[] dijkstra(int n, List<List<Edge>> adj, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (Edge e : adj.get(u)) {
                if (dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.add(new int[]{e.to, dist[e.to]});
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        List<List<Edge>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Collections.singletonList(new Edge(1, 10))));
        adj.add(new ArrayList<>());
        int[] d = dijkstra(2, adj, 0);
        System.out.println("Dist to 1: " + d[1]);
    }
}
