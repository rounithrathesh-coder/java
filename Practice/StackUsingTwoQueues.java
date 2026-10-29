/**
 * Practice Implementation: LIFO Stack Emulated Using Two Standard FIFO Queues
 * Revision: #229 | Date: 2026-10-29 08:44:45
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.LinkedList;
import java.util.Queue;
public class StackUsingTwoQueues {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()) q2.add(q1.remove());
        Queue<Integer> temp = q1; q1 = q2; q2 = temp;
    }
    public int pop() { return q1.isEmpty() ? -1 : q1.remove(); }
    public static void main(String[] args) {
        StackUsingTwoQueues s = new StackUsingTwoQueues();
        s.push(1); s.push(2);
        System.out.println("Popped: " + s.pop());
    }
}
