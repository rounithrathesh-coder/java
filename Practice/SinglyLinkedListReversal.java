/**
 * Practice Implementation: In-Place Singly Linked List Reversal Algorithm
 * Summer Revision: #90 | Date: 2026-07-13 20:15:55
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class SinglyLinkedListReversal {
    static class Node { int val; Node next; Node(int v) { val = v; } }
    public static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        Node h = new Node(1); h.next = new Node(2); h.next.next = new Node(3);
        Node r = reverse(h);
        System.out.println("Reversed head: " + r.val);
    }
}
