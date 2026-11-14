/**
 * Daily Java Challenge - Day 022 (2026-11-14)
 * Topic: Concurrency - Lock-Free Treiber Stack with AtomicReference
 * Category: Advanced Concurrency & Non-Blocking Algorithms
 *
 * Description:
 * Production implementation of the classic Treiber Stack (1986), a non-blocking,
 * lock-free LIFO data structure using optimistic Compare-And-Swap (CAS) primitives.
 *
 * In contrast to synchronized or locked collections (which force threads to suspend
 * and context-switch on contention), a lock-free algorithm allows all threads to make
 * concurrent progress by retrying only when an intervening CAS invalidates the current head.
 *
 * Invariants:
 * 1. Head pointer is wrapped in an AtomicReference.
 * 2. Push/Pop employ lock-free loop retry patterns:
 *      do {
 *          oldHead = top.get();
 *          ...
 *      } while (!top.compareAndSet(oldHead, newHead));
 * 3. Linearizable and memory-safe under high concurrent contention.
 *
 * Time Complexity:
 * - push(): O(1) amortized
 * - pop(): O(1) amortized
 * Space Complexity: O(N) where N is the number of elements
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Day022_Lock_Free_Stack_Treiber<E> {

    // Node representation
    private static class Node<E> {
        final E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }

    private final AtomicReference<Node<E>> top = new AtomicReference<>(null);
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * Pushes an element onto the stack in a lock-free, non-blocking manner.
     *
     * @param item value to push
     */
    public void push(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Null elements not permitted.");
        }

        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;

        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));

        size.incrementAndGet();
    }

    /**
     * Pops an element from the stack in a lock-free manner.
     *
     * @return popped item, or null if stack is empty
     */
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;

        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null; // Stack is empty
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));

        size.decrementAndGet();
        return oldHead.item;
    }

    /**
     * Peeks at top item without removing it.
     */
    public E peek() {
        Node<E> current = top.get();
        return (current == null) ? null : current.item;
    }

    public boolean isEmpty() {
        return top.get() == null;
    }

    public int size() {
        return size.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 022: Lock-Free Treiber Stack with CAS");
        System.out.println("=======================================================================");

        Day022_Lock_Free_Stack_Treiber<Integer> stack = new Day022_Lock_Free_Stack_Treiber<>();

        // Test 1: Basic LIFO behavior
        System.out.println("Test 1: Basic single-threaded push, peek, and pop...");
        assert stack.isEmpty() : "Initial stack must be empty!";
        assert stack.pop() == null : "Popping empty stack must return null!";

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assert stack.size() == 3 : "Size must be 3!";
        assert stack.peek() == 30 : "Peek must return top element (30)!";
        assert stack.pop() == 30 : "Popped element must be 30!";
        assert stack.pop() == 20 : "Popped element must be 20!";
        assert stack.pop() == 10 : "Popped element must be 10!";
        assert stack.isEmpty() : "Stack must be empty after popping all!";
        System.out.println("-> Test 1 Passed: LIFO order preserved.");

        // Test 2: High-concurrency multithreaded push stress test
        System.out.println("\nTest 2: Concurrent PUSH stress test (16 threads pushing 1,000 items each)...");
        int threadCount = 16;
        int itemsPerThread = 1_000;
        int totalItems = threadCount * itemsPerThread;

        ExecutorService pushExecutor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch pushLatch = new CountDownLatch(threadCount);

        for (int t = 0; t < threadCount; t++) {
            final int threadId = t;
            pushExecutor.submit(() -> {
                try {
                    for (int i = 0; i < itemsPerThread; i++) {
                        stack.push(threadId * itemsPerThread + i);
                    }
                } finally {
                    pushLatch.countDown();
                }
            });
        }

        pushLatch.await();
        pushExecutor.shutdown();

        System.out.printf("  Total items pushed: %d, Stack measured size: %d%n", totalItems, stack.size());
        assert stack.size() == totalItems : "Stack size must exactly equal total pushed items!";
        System.out.println("-> Test 2 Passed: Lock-free CAS pushed all elements without loss.");

        // Test 3: High-concurrency multithreaded pop stress test
        System.out.println("\nTest 3: Concurrent POP stress test (16 threads popping until empty)...");
        ExecutorService popExecutor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch popLatch = new CountDownLatch(threadCount);
        List<Integer> poppedItems = Collections.synchronizedList(new ArrayList<>());

        for (int t = 0; t < threadCount; t++) {
            popExecutor.submit(() -> {
                try {
                    while (true) {
                        Integer val = stack.pop();
                        if (val == null) {
                            // If empty, verify by double checking
                            if (stack.isEmpty()) break;
                            continue;
                        }
                        poppedItems.add(val);
                    }
                } finally {
                    popLatch.countDown();
                }
            });
        }

        popLatch.await();
        popExecutor.shutdown();

        System.out.printf("  Total items popped: %d, Remaining size: %d%n", poppedItems.size(), stack.size());
        assert poppedItems.size() == totalItems : "All pushed items must be successfully popped!";
        assert stack.isEmpty() : "Stack must be completely empty!";
        System.out.println("-> Test 3 Passed: Lock-free CAS popped all elements with zero dropped items.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Lock-Free Treiber Stack Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
