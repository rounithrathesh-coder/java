/**
 * Practice Implementation: Binary Min-Heap Array-Based Priority Queue
 * Revision: #89 | Date: 2026-09-05 09:20:54
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class MinHeapPriorityQueue {
    private int[] heap;
    private int size;
    public MinHeapPriorityQueue(int cap) { heap = new int[cap]; size = 0; }
    public void insert(int val) {
        heap[size] = val;
        int curr = size++;
        while (curr > 0 && heap[curr] < heap[(curr - 1) / 2]) {
            int p = (curr - 1) / 2;
            int t = heap[curr]; heap[curr] = heap[p]; heap[p] = t;
            curr = p;
        }
    }
    public int peek() { return size > 0 ? heap[0] : -1; }
    public static void main(String[] args) {
        MinHeapPriorityQueue pq = new MinHeapPriorityQueue(10);
        pq.insert(15); pq.insert(5); pq.insert(20);
        System.out.println("Min: " + pq.peek());
    }
}
