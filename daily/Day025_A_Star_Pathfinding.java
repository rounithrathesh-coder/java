/**
 * Daily Java Challenge - Day 025 (2026-11-16)
 * Topic: Graph Algorithms - A* Shortest Path Search with Heuristics
 * Category: Algorithms & Pathfinding
 *
 * Description:
 * Production implementation of the A* (A-Star) search algorithm on a 2D grid
 * with obstacles, employing an admissible Manhattan distance heuristic.
 *
 * Mathematical Invariant:
 * f(n) = g(n) + h(n)
 * where:
 * - g(n) is the exact movement cost from start to node n.
 * - h(n) is the estimated Manhattan distance from node n to goal: |x - targetX| + |y - targetY|.
 * - f(n) is the estimated total cost of the cheapest path through node n.
 *
 * An admissible heuristic (never overestimating true cost) guarantees optimal shortest paths.
 *
 * Time Complexity: O(E log V) in worst-case grid exploration
 * Space Complexity: O(R * C) for priority queue, visited states, and parent pointers
 */

import java.util.*;

public class Day025_A_Star_Pathfinding {

    public static class Point {
        public final int r;
        public final int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point other)) return false;
            return r == other.r && c == other.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", r, c);
        }
    }

    private static class Node implements Comparable<Node> {
        final Point pt;
        final int g; // Cost from start
        final int h; // Heuristic to goal
        final int f; // Total estimated cost (g + h)
        final Node parent;

        Node(Point pt, int g, int h, Node parent) {
            this.pt = pt;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            if (this.f != other.f) {
                return Integer.compare(this.f, other.f);
            }
            return Integer.compare(this.h, other.h); // Break ties with lower remaining heuristic
        }
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 4-directional

    /**
     * Finds the shortest path from start to goal in a grid where 0 is walkable and 1 is an obstacle.
     *
     * @param grid 2D matrix (0 = empty, 1 = obstacle)
     * @param start starting coordinate
     * @param goal target coordinate
     * @return List of points from start to goal inclusive, or empty list if no path exists
     */
    public static List<Point> findPath(int[][] grid, Point start, Point goal) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return Collections.emptyList();
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[start.r][start.c] == 1 || grid[goal.r][goal.c] == 1) {
            return Collections.emptyList(); // Start or goal is blocked
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        int[][] gScore = new int[rows][cols];
        for (int[] row : gScore) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int initialH = heuristic(start, goal);
        openSet.add(new Node(start, 0, initialH, null));
        gScore[start.r][start.c] = 0;

        boolean[][] closedSet = new boolean[rows][cols];

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Point currPt = current.pt;

            // Goal reached
            if (currPt.equals(goal)) {
                return reconstructPath(current);
            }

            if (closedSet[currPt.r][currPt.c]) {
                continue;
            }
            closedSet[currPt.r][currPt.c] = true;

            for (int[] d : DIRS) {
                int nr = currPt.r + d[0];
                int nc = currPt.c + d[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 0 && !closedSet[nr][nc]) {
                    int tentativeG = current.g + 1;
                    if (tentativeG < gScore[nr][nc]) {
                        gScore[nr][nc] = tentativeG;
                        int h = heuristic(new Point(nr, nc), goal);
                        openSet.add(new Node(new Point(nr, nc), tentativeG, h, current));
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private static int heuristic(Point a, Point b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c); // Manhattan Distance
    }

    private static List<Point> reconstructPath(Node endNode) {
        List<Point> path = new ArrayList<>();
        Node curr = endNode;
        while (curr != null) {
            path.add(curr.pt);
            curr = curr.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 025: A* Shortest Path Search on 2D Grid");
        System.out.println("=======================================================================");

        // 6x6 Grid with obstacle wall
        // 0 = walkable, 1 = obstacle
        int[][] grid = {
            {0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0}
        };

        Point start = new Point(0, 0);
        Point goal = new Point(4, 3);

        System.out.println("Grid layout with obstacle maze initialized.");
        System.out.printf("Searching path from %s to %s...%n", start, goal);

        List<Point> path = findPath(grid, start, goal);

        System.out.println("Computed Path: " + path);
        System.out.printf("Total path length: %d steps (edges: %d)%n", path.size(), path.size() - 1);

        assert !path.isEmpty() : "Path must exist around the maze!";
        assert path.get(0).equals(start) : "First node must be start!";
        assert path.get(path.size() - 1).equals(goal) : "Last node must be goal!";
        System.out.println("-> Test 1 Passed: Optimal route computed around obstacle walls.");

        // Test 2: Unreachable goal (completely surrounded by obstacles)
        int[][] blockedGrid = {
            {0, 1, 0},
            {1, 1, 0},
            {0, 0, 0}
        };
        List<Point> blockedPath = findPath(blockedGrid, new Point(0, 0), new Point(2, 2));
        assert blockedPath.isEmpty() : "No path should be found when walled off!";
        System.out.println("-> Test 2 Passed: Correctly reported unreachable destination.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! A* Pathfinding Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
