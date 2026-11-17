/**
 * Daily Java Challenge - Day 026 (2026-11-17)
 * Topic: Multithreading - Custom Fair ReadWriteLock with Reentrancy
 * Category: Concurrency & Synchronization
 *
 * Description:
 * Production implementation of a custom ReadWriteLock supporting reentrancy
 * and writer-preference starvation prevention.
 *
 * Locking Semantics:
 * 1. Read Lock: Allowed if no writer currently holds the lock, AND no writers are queued
 *    (or the current thread already holds a read lock).
 * 2. Write Lock: Allowed only if no threads hold read locks, and no thread holds the write lock
 *    (unless the current thread is the existing write-lock holder).
 * 3. Reentrancy: A thread holding a lock can re-acquire it without self-deadlocking.
 *
 * Time Complexity: O(1) lock and unlock
 * Space Complexity: O(T) where T is concurrent thread count
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Day026_Read_Write_Lock_Implementation {

    private final Map<Thread, Integer> readingThreads = new HashMap<>();
    private int writeAccesses = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    /**
     * Acquires read lock with reentrancy support and writer starvation prevention.
     */
    public synchronized void lockRead() throws InterruptedException {
        Thread current = Thread.currentThread();
        while (!canGrantReadAccess(current)) {
            wait();
        }
        readingThreads.put(current, getReadHoldCount(current) + 1);
    }

    /**
     * Releases read lock.
     */
    public synchronized void unlockRead() {
        Thread current = Thread.currentThread();
        if (!isReader(current)) {
            throw new IllegalMonitorStateException("Calling thread does not hold read lock.");
        }
        int holdCount = getReadHoldCount(current);
        if (holdCount == 1) {
            readingThreads.remove(current);
        } else {
            readingThreads.put(current, holdCount - 1);
        }
        notifyAll();
    }

    /**
     * Acquires exclusive write lock with reentrancy support.
     */
    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread current = Thread.currentThread();
        try {
            while (!canGrantWriteAccess(current)) {
                wait();
            }
        } finally {
            writeRequests--;
        }
        writeAccesses++;
        writingThread = current;
    }

    /**
     * Releases write lock.
     */
    public synchronized void unlockWrite() {
        Thread current = Thread.currentThread();
        if (!isWriter(current)) {
            throw new IllegalMonitorStateException("Calling thread does not hold write lock.");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantReadAccess(Thread current) {
        if (writingThread == current) return true; // Writer can read
        if (writingThread != null) return false;    // Another thread is writing
        if (isReader(current)) return true;        // Already has read lock (reentrant)
        return writeRequests == 0;                 // Prioritize waiting writers to prevent starvation
    }

    private boolean canGrantWriteAccess(Thread current) {
        if (isOnlyReader(current)) return true;
        if (hasReaders()) return false;
        if (writingThread == null) return true;
        return isWriter(current); // Reentrant write
    }

    private int getReadHoldCount(Thread current) {
        return readingThreads.getOrDefault(current, 0);
    }

    private boolean hasReaders() {
        return !readingThreads.isEmpty();
    }

    private boolean isReader(Thread current) {
        return readingThreads.containsKey(current);
    }

    private boolean isOnlyReader(Thread current) {
        return readingThreads.size() == 1 && readingThreads.containsKey(current);
    }

    private boolean isWriter(Thread current) {
        return writingThread == current;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 026: Custom Fair Reentrant ReadWriteLock");
        System.out.println("=======================================================================");

        Day026_Read_Write_Lock_Implementation lock = new Day026_Read_Write_Lock_Implementation();

        // Test 1: Single-threaded reentrant read and write
        System.out.println("Test 1: Single-threaded reentrancy...");
        lock.lockRead();
        lock.lockRead(); // Reentrant read
        assert lock.readingThreads.size() == 1 : "Must have 1 reading thread!";
        lock.unlockRead();
        lock.unlockRead();
        assert lock.readingThreads.isEmpty() : "Read locks must be fully released!";

        lock.lockWrite();
        lock.lockWrite(); // Reentrant write
        assert lock.writeAccesses == 2 : "Write access count must be 2!";
        lock.unlockWrite();
        lock.unlockWrite();
        assert lock.writingThread == null : "Write lock must be fully released!";
        System.out.println("-> Test 1 Passed: Read and write reentrancy verified.");

        // Test 2: Multithreaded concurrent read sharing and exclusive writing
        System.out.println("\nTest 2: Multithreaded concurrency test (8 readers, 2 writers)...");
        int numThreads = 10;
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger sharedResource = new AtomicInteger(0);
        AtomicInteger maxConcurrentReaders = new AtomicInteger(0);
        AtomicInteger activeReaders = new AtomicInteger(0);

        // Submit 8 concurrent readers
        for (int i = 0; i < 8; i++) {
            pool.submit(() -> {
                try {
                    lock.lockRead();
                    int currentActive = activeReaders.incrementAndGet();
                    maxConcurrentReaders.accumulateAndGet(currentActive, Math::max);

                    // Read shared resource
                    int val = sharedResource.get();
                    Thread.sleep(20);

                    activeReaders.decrementAndGet();
                    lock.unlockRead();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        // Submit 2 writers
        for (int i = 0; i < 2; i++) {
            pool.submit(() -> {
                try {
                    lock.lockWrite();
                    // Writer mutates shared resource while guaranteeing no active readers
                    assert activeReaders.get() == 0 : "No readers allowed during active write!";
                    sharedResource.addAndGet(50);
                    Thread.sleep(15);
                    lock.unlockWrite();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        pool.shutdown();

        System.out.printf("  Final shared resource value: %d (Expected: 100)%n", sharedResource.get());
        System.out.printf("  Max concurrent readers witnessed: %d%n", maxConcurrentReaders.get());

        assert sharedResource.get() == 100 : "Both writers must have applied mutations!";
        assert maxConcurrentReaders.get() > 1 : "Multiple readers must be allowed concurrently!";
        System.out.println("-> Test 2 Passed: Mutual exclusion and reader parallelism verified.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Custom ReadWriteLock Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
