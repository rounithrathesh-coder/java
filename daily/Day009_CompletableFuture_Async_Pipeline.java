/**
 * Daily Java Challenge - CompletableFuture Pipelines
 * Topic: Asynchronous Programming in Java 8+
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Day009_CompletableFuture_Async_Pipeline {

    public static CompletableFuture<String> fetchUser(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(100);
            return "User-" + userId;
        });
    }

    public static CompletableFuture<Double> fetchUserCredit(String user) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(100);
            return 2500.75;
        });
    }

    private static void simulateDelay(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        CompletableFuture<String> pipeline = fetchUser(42)
            .thenCompose(Day009_CompletableFuture_Async_Pipeline::fetchUserCredit)
            .thenApply(credit -> "Account credit balance: $" + credit)
            .exceptionally(ex -> "Error processing pipeline: " + ex.getMessage());

        String result = pipeline.join();
        System.out.println(result);
    }
}
