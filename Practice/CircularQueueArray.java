/**
 * Practice Implementation: Ring Buffer Circular Queue Implementation
 * Revision: #26 | Date: 2026-06-26 16:32:56
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class CircularQueueArray {
    private final int[] arr;
    private int front, rear, count;
    public CircularQueueArray(int k) { arr = new int[k]; front = 0; rear = 0; count = 0; }
    public boolean enqueue(int x) {
        if (count == arr.length) return false;
        arr[rear] = x;
        rear = (rear + 1) % arr.length;
        count++;
        return true;
    }
    public int dequeue() {
        if (count == 0) return -1;
        int val = arr[front];
        front = (front + 1) % arr.length;
        count--;
        return val;
    }
    public static void main(String[] args) {
        CircularQueueArray q = new CircularQueueArray(3);
        q.enqueue(100); q.enqueue(200);
        System.out.println("Dequeued: " + q.dequeue());
    }
}
