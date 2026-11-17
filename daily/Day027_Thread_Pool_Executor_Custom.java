/**
 * Daily Java Challenge - Day 027 (2026-11-17)
 * Topic: Multithreading - Custom Thread Pool Executor & Worker Task Queue
 * Category: Advanced Concurrency & Systems Architecture
 *
 * Description:
 * Production implementation of a custom ThreadPoolExecutor from scratch without
 * using java.util.concurrent.Executors.
 *
 * Core Components:
 * 1. BlockingTaskQueue: Thread-safe bounded FIFO queue using wait/notifyAll monitor pattern.
 * 2. WorkerThreads: Pre-spawned worker daemon threads continuously pulling and executing tasks.
 * 3. Lifecycle State: Tracks ACTIVE vs SHUTDOWN states, rejecting new submissions upon shutdown.
 * 4. Graceful Shutdown & Await: Coordinates termination when all pending tasks drain.
 *
 * Time Complexity: O(1) task submission and dispatch
 * Space Complexity: O(PoolSize + QueueCapacity)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Day027_Thread_Pool_Executor_Custom {

    // Internal Blocking Task Queue
    private static class BlockingTaskQueue {
        private final Queue<Runnable> queue = new LinkedList<>();
        private final int capacity;

        BlockingTaskQueue(int capacity) {
            this.capacity = capacity;
        }

        synchronized void enqueue(Runnable task) throws InterruptedException {
            while (queue.size() >= capacity) {
                wait();
            }
            queue.offer(task);
            notifyAll();
        }

        synchronized Runnable dequeue() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            Runnable task = queue.poll();
            notifyAll();
            return task;
        }

        synchronized int size() {
            return queue.size();
        }

        synchronized boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // Worker Thread abstraction
    private class Worker extends Thread {
        Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isShutdown.get() || !taskQueue.isEmpty()) {
                try {
                    Runnable task;
                    synchronized (taskQueue) {
                        while (taskQueue.isEmpty() && !isShutdown.get()) {
                            taskQueue.wait(50);
                        }
                        if (taskQueue.isEmpty() && isShutdown.get()) {
                            break;
                        }
                        task = taskQueue.queue.poll();
                        taskQueue.notifyAll();
                    }
                    if (task != null) {
                        task.run();
                        completedTasksCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    // Thread interrupted during shutdown
                    break;
                }
            }
        }
    }

    private final BlockingTaskQueue taskQueue;
    private final List<Worker> workers;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);
    private final AtomicInteger completedTasksCount = new AtomicInteger(0);

    public Day027_Thread_Pool_Executor_Custom(int poolSize, int queueCapacity) {
        if (poolSize <= 0 || queueCapacity <= 0) {
            throw new IllegalArgumentException("Pool size and queue capacity must be positive.");
        }
        this.taskQueue = new BlockingTaskQueue(queueCapacity);
        this.workers = new ArrayList<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker("CustomPool-Worker-" + (i + 1));
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * Submits a new task to the thread pool.
     */
    public void submit(Runnable task) throws InterruptedException {
        if (isShutdown.get()) {
            throw new IllegalStateException("Cannot accept new tasks: Thread pool is shut down.");
        }
        taskQueue.enqueue(task);
    }

    /**
     * Initiates an orderly shutdown where existing tasks are executed but no new tasks are accepted.
     */
    public void shutdown() {
        isShutdown.set(true);
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
    }

    /**
     * Blocks until all tasks have completed execution after a shutdown request.
     */
    public void awaitTermination() throws InterruptedException {
        for (Worker worker : workers) {
            worker.join();
        }
    }

    public int getCompletedTasksCount() {
        return completedTasksCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======================================================================");
        System.out.println("Daily Java Challenge - Day 027: Custom Thread Pool Executor");
        System.out.println("=======================================================================");

        int poolSize = 4;
        int queueCapacity = 50;
        int totalTasks = 20;

        Day027_Thread_Pool_Executor_Custom executor = new Day027_Thread_Pool_Executor_Custom(poolSize, queueCapacity);
        System.out.printf("Initialized Thread Pool with %d worker threads (Queue Capacity: %d).%n", poolSize, queueCapacity);

        AtomicInteger accumulator = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(totalTasks);

        System.out.printf("Submitting %d concurrent work tasks...%n", totalTasks);
        for (int i = 1; i <= totalTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    accumulator.addAndGet(taskId);
                    Thread.sleep(10); // Simulate processing work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        // Wait for all submitted tasks to complete
        latch.await();
        System.out.println("-> All tasks reached execution completion.");

        // Graceful shutdown
        executor.shutdown();
        executor.awaitTermination();

        int expectedSum = totalTasks * (totalTasks + 1) / 2; // Sum 1..20 = 210
        System.out.printf("Total accumulated sum: %d (Expected: %d)%n", accumulator.get(), expectedSum);
        System.out.printf("Total completed tasks: %d (Expected: %d)%n", executor.getCompletedTasksCount(), totalTasks);

        assert accumulator.get() == expectedSum : "Accumulator sum must match expected formula!";
        assert executor.getCompletedTasksCount() == totalTasks : "Completed task count must match total submitted!";
        System.out.println("-> Test 1 Passed: Work distribution across threads verified.");

        // Test 2: Verify rejected submission after shutdown
        boolean rejected = false;
        try {
            executor.submit(() -> System.out.println("Should fail"));
        } catch (IllegalStateException e) {
            rejected = true;
        }
        assert rejected : "Submitting task after shutdown must throw IllegalStateException!";
        System.out.println("-> Test 2 Passed: Post-shutdown task rejection verified.");

        System.out.println("\n=======================================================================");
        System.out.println("ALL ASSERTIONS PASSED! Custom Thread Pool Executor Verified & Operational.");
        System.out.println("=======================================================================");
    }
}
