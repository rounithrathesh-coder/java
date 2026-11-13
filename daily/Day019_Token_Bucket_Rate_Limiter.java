/**
 * Daily Java Challenge - Day 019 (2026-11-12)
 * Topic: System Design & Concurrency - Token Bucket Rate Limiter
 * Category: Concurrency & Systems Architecture
 *
 * Description:
 * Production-grade implementation of the Token Bucket algorithm used for API rate limiting,
 * traffic shaping, and burst control (similar to Stripe and AWS API Gateway).
 *
 * Invariants:
 * 1. Tokens are refilled lazily on each access based on elapsed wall-clock / nano time.
 * 2. Token count is bounded by [0, maxCapacity].
 * 3. Thread-safe operations protected by synchronization or reentrant locks.
 *
 * Time Complexity: O(1) for tryAcquire()
 * Space Complexity: O(1) state per rate limiter instance
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Day019_Token_Bucket_Rate_Limiter {

    private final long capacity;
    private final double refillTokensPerSecond;
    private double availableTokens;
    private long lastRefillNanos;

    /**
     * Initializes the Token Bucket.
     *
     * @param capacity Maximum burst capacity (bucket size)
     * @param refillTokensPerSecond Rate at which tokens are replenished per second
     */
    public Day019_Token_Bucket_Rate_Limiter(long capacity, double refillTokensPerSecond) {
        if (capacity <= 0 || refillTokensPerSecond <= 0) {
            throw new IllegalArgumentException("Capacity and refill rate must be positive.");
        }
        this.capacity = capacity;
        this.refillTokensPerSecond = refillTokensPerSecond;
        this.availableTokens = capacity;
        this.lastRefillNanos = System.nanoTime();
    }

    /**
     * Attempts to acquire 1 token without blocking.
     *
     * @return true if token was successfully consumed; false if rate-limited
     */
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    /**
     * Attempts to acquire specified number of tokens without blocking.
     *
     * @param tokens Number of tokens to consume
     * @return true if tokens were acquired; false otherwise
     */
    public synchronized boolean tryAcquire(int tokens) {
        if (tokens <= 0) {
            throw new IllegalArgumentException("Tokens requested must be positive.");
        }

        refill();

        if (availableTokens >= tokens) {
            availableTokens -= tokens;
            return true;
        }
        return false;
    }

    /**
     * Lazily refills tokens proportional to the nano-seconds elapsed since last check.
     */
    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillNanos) / 1_000_000_000.0;

        if (elapsedSeconds > 0) {
            double newTokens = elapsedSeconds * refillTokensPerSecond;
            availableTokens = Math.min(capacity, availableTokens + newTokens);
            lastRefillNanos = now;
        }
    }

    public synchronized double getAvailableTokens() {
        refill();
        return availableTokens;
    }

    public long getCapacity() {
        return capacity;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================================");
        System.out.println("Daily Java Challenge - Day 019: Token Bucket Rate Limiter");
        System.out.println("==========================================================");

        // Test 1: Burst Capacity Test (Capacity = 5, Refill = 2 tokens/sec)
        Day019_Token_Bucket_Rate_Limiter limiter = new Day019_Token_Bucket_Rate_Limiter(5, 2.0);

        System.out.println("Test 1: Consuming burst capacity of 5 tokens...");
        for (int i = 1; i <= 5; i++) {
            boolean acquired = limiter.tryAcquire();
            System.out.printf("  Request #%d: %s (Available: %.2f)%n", i, acquired ? "ALLOWED" : "REJECTED", limiter.getAvailableTokens());
            assert acquired : "Token should have been available!";
        }

        // Test 2: Rate limit rejection immediately after capacity is exhausted
        boolean rejected = limiter.tryAcquire();
        System.out.printf("  Request #6 (immediate overflow): %s%n", rejected ? "ALLOWED" : "REJECTED");
        assert !rejected : "Request #6 must be rejected due to rate limiting!";
        System.out.println("-> Test 1 & 2 Passed: Burst limit enforced correctly.");

        // Test 3: Refill after sleeping 1.1 seconds (should replenish ~2 tokens)
        System.out.println("\nTest 3: Sleeping 1100ms for lazy token replenishment...");
        Thread.sleep(1100);

        boolean refilledReq1 = limiter.tryAcquire();
        boolean refilledReq2 = limiter.tryAcquire();
        System.out.printf("  Post-sleep Request #1: %s%n", refilledReq1 ? "ALLOWED" : "REJECTED");
        System.out.printf("  Post-sleep Request #2: %s%n", refilledReq2 ? "ALLOWED" : "REJECTED");
        assert refilledReq1 : "At least 1 token must have refilled!";
        System.out.println("-> Test 3 Passed: Tokens replenished accurately.");

        // Test 4: High-concurrency multithreaded stress test
        System.out.println("\nTest 4: High-concurrency multithreaded acquisition test (20 concurrent threads)...");
        Day019_Token_Bucket_Rate_Limiter concurrentLimiter = new Day019_Token_Bucket_Rate_Limiter(10, 5.0);
        int threadCount = 20;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger throttledCount = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    if (concurrentLimiter.tryAcquire()) {
                        successCount.incrementAndGet();
                    } else {
                        throttledCount.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        System.out.printf("  Concurrent results -> Allowed: %d, Throttled: %d (Total: %d)%n",
                successCount.get(), throttledCount.get(), threadCount);
        assert successCount.get() <= 10 : "Allowed requests cannot exceed initial capacity of 10!";
        assert successCount.get() + throttledCount.get() == threadCount : "All requests must be accounted for!";
        System.out.println("-> Test 4 Passed: Thread-safety verified under concurrent contention.");

        System.out.println("\n==========================================================");
        System.out.println("ALL 4 ASSERTIONS PASSED! Token Bucket Rate Limiter Verified.");
        System.out.println("==========================================================");
    }
}
