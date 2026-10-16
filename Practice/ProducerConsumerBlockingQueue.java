/**
 * Practice Implementation: Multi-Threaded Producer Consumer with ArrayBlockingQueue
 * Revision: #202 | Date: 2026-10-16 22:04:01
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.concurrent.*;
public class ProducerConsumerBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);
        q.put(42);
        int val = q.take();
        System.out.println("Produced and consumed: " + val);
    }
}
