/**
 * REC R2023 IT Curriculum - Unit III: Packages, Interfaces & Exception Handling
 * Topic: Custom Exception Hierarchy, Try-With-Resources, and Nested Interfaces
 *
 * Concepts Demonstrated:
 * 1. Interface definition and Implementation with Default & Static methods.
 * 2. Nested Interfaces for encapsulated API contracts.
 * 3. Checked Exception vs. Unchecked (RuntimeException) distinction.
 * 4. Automatic Resource Management using try-with-resources and AutoCloseable.
 */

public class Unit3_Exception_Handling_And_Nested_Interfaces {

    // 1. Nested Interface Pattern
    public interface PaymentGateway {
        void processPayment(double amount) throws PaymentFailedException; // Checked exception

        // Nested interface representing a transaction callback listener
        interface TransactionListener {
            void onTransactionSuccess(String txnId, double amount);
            void onTransactionFailure(String reason);
        }

        // Static factory method in interface (Java 8+)
        static PaymentGateway createMockGateway() {
            return new MockStripeGateway();
        }
    }

    // 2. Custom Checked Exception
    public static class PaymentFailedException extends Exception {
        private final String errorCode;

        public PaymentFailedException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }

        public String getErrorCode() { return errorCode; }
    }

    // 3. Custom Unchecked Exception
    public static class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    // 4. AutoCloseable Resource for Try-With-Resources
    public static class SecureAuditSession implements AutoCloseable {
        private boolean isOpen = true;

        public SecureAuditSession() {
            System.out.println("  [AuditSession] Secured audit session opened.");
        }

        public void log(String message) {
            if (!isOpen) throw new IllegalStateException("Session is closed.");
            System.out.println("  [AuditSession LOG] " + message);
        }

        @Override
        public void close() {
            isOpen = false;
            System.out.println("  [AuditSession] Secured audit session automatically closed via AutoCloseable.");
        }
    }

    // Concrete implementation
    private static class MockStripeGateway implements PaymentGateway {
        private double balance = 1000.0;

        @Override
        public void processPayment(double amount) throws PaymentFailedException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment amount must be positive.");
            }
            if (amount > balance) {
                throw new PaymentFailedException("Card declined: Insufficient funds in merchant wallet.", "ERR_CARD_DECLINED");
            }
            balance -= amount;
        }
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("REC R2023 IT - Unit III: Exception Handling & Nested Interfaces");
        System.out.println("=======================================================================");

        PaymentGateway gateway = PaymentGateway.createMockGateway();

        // Test 1: Try-with-resources and Successful Execution
        System.out.println("Test 1: Normal payment flow with try-with-resources...");
        try (SecureAuditSession session = new SecureAuditSession()) {
            gateway.processPayment(250.0);
            session.log("Payment of $250.00 processed successfully.");
        } catch (PaymentFailedException e) {
            System.err.println("Payment failed: " + e.getMessage());
        }

        // Test 2: Catching Custom Checked Exception
        System.out.println("\nTest 2: Triggering and handling custom checked exception...");
        boolean exceptionCaught = false;
        try {
            gateway.processPayment(5000.0); // Exceeds balance
        } catch (PaymentFailedException e) {
            exceptionCaught = true;
            System.out.printf("  Caught expected exception: [%s] %s%n", e.getErrorCode(), e.getMessage());
            assert "ERR_CARD_DECLINED".equals(e.getErrorCode()) : "Error code mismatch!";
        }
        assert exceptionCaught : "Checked exception should have been caught!";

        // Test 3: Nested Interface Callback Listener
        System.out.println("\nTest 3: Nested interface listener callback...");
        PaymentGateway.TransactionListener listener = new PaymentGateway.TransactionListener() {
            @Override
            public void onTransactionSuccess(String txnId, double amount) {
                System.out.printf("  [Listener Callback] Transaction %s confirmed for $%.2f%n", txnId, amount);
            }

            @Override
            public void onTransactionFailure(String reason) {
                System.out.println("  [Listener Callback] Transaction failed: " + reason);
            }
        };

        listener.onTransactionSuccess("TXN_98765", 150.00);

        System.out.println("\nAll Unit III assertions passed successfully!");
    }
}
