# Java Collections Framework (JCF) Reference

## Hierarchy Overview
- `Collection` (Interface)
  - `List`: Ordered collection allowing duplicates (`ArrayList`, `LinkedList`, `Vector`).
  - `Set`: Unordered collection preventing duplicates (`HashSet`, `LinkedHashSet`, `TreeSet`).
  - `Queue` / `Deque`: FIFO and double-ended queues (`PriorityQueue`, `ArrayDeque`, `LinkedList`).
- `Map` (Interface - Key/Value pairs, not extending Collection)
  - `HashMap`: $O(1)$ amortized get/put using bucket array + red-black tree (since Java 8 for buckets > 8).
  - `LinkedHashMap`: Preserves insertion order via doubly linked list running through buckets.
  - `TreeMap`: $O(\log N)$ sorted map backed by Red-Black Self-Balancing Binary Search Tree.

## Time Complexity Summary
| Data Structure | Access | Search | Insertion | Deletion |
|---|---|---|---|---|
| `ArrayList` | $O(1)$ | $O(N)$ | $O(1)$ amortized | $O(N)$ |
| `LinkedList` | $O(N)$ | $O(N)$ | $O(1)$ at ends | $O(1)$ at node |
| `HashSet` | N/A | $O(1)$ | $O(1)$ | $O(1)$ |
| `TreeSet` | N/A | $O(\log N)$ | $O(\log N)$ | $O(\log N)$ |
| `PriorityQueue` | $O(1)$ peek | $O(N)$ | $O(\log N)$ offer | $O(\log N)$ poll |


<!-- Authored: 2026-06-08 10:58:40 -->

<!-- Section Revision: 2026-06-17 11:31:29 -->

<!-- Section Revision: 2026-06-30 15:51:05 -->

<!-- Section Revision: 2026-08-06 15:47:18 -->

<!-- Section Revision: 2026-09-18 19:03:11 -->

<!-- Section Revision: 2026-09-24 22:01:06 -->

<!-- Section Revision: 2026-09-29 20:36:45 -->

<!-- Section Revision: 2026-10-07 19:25:40 -->

<!-- Topic Section Update: 2026-06-16 17:16:32 -->

<!-- Topic Section Update: 2026-07-05 17:59:43 -->
