/**
 * Practice Implementation: Multi-Threaded Producer Consumer with ArrayBlockingQueue
 * Summer Revision: #48 | Date: 2026-06-25 14:21:12
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
