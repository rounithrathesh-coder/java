/**
 * Daily Java Challenge - Day 013 (2026-11-10)
 * Topic: Data Structures - Generic Singly Linked List
 * Category: Data Structures
 * 
 * Description:
 * Implementation of a custom Generic Singly Linked List featuring:
 * - Generic Type Parameters <T>
 * - Insert at head and tail (O(1))
 * - Delete by value (O(n))
 * - In-place iterative reversal (O(n) time, O(1) space)
 * - Iterable<T> and Iterator<T> support for enhanced for-loops
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Day013_Custom_Generic_LinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Day013_Custom_Generic_LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public boolean remove(T data) {
        if (isEmpty()) return false;

        if (head.data.equals(data)) {
            head = head.next;
            if (head == null) tail = null;
            size--;
            return true;
        }

        Node<T> curr = head;
        while (curr.next != null && !curr.next.data.equals(data)) {
            curr = curr.next;
        }

        if (curr.next != null) {
            if (curr.next == tail) tail = curr;
            curr.next = curr.next.next;
            size--;
            return true;
        }

        return false;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;
        tail = head;

        while (curr != null) {
            Node<T> nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        head = prev;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Custom Generic Singly Linked List ===");
        Day013_Custom_Generic_LinkedList<String> list = new Day013_Custom_Generic_LinkedList<>();

        list.addLast("Java");
        list.addLast("Spring");
        list.addLast("PostgreSQL");
        list.addFirst("Docker");

        System.out.print("Original List: ");
        for (String item : list) {
            System.out.print(item + " -> ");
        }
        System.out.println("null");

        System.out.println("Reversing list in-place...");
        list.reverse();

        System.out.print("Reversed List: ");
        for (String item : list) {
            System.out.print(item + " -> ");
        }
        System.out.println("null");

        list.remove("Spring");
        System.out.println("After removing 'Spring', size is: " + list.size());
        System.out.println("All operations completed successfully!");
    }
}
