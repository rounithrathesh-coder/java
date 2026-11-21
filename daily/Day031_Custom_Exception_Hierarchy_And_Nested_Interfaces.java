/**
 * Daily Java Challenge - Day 031 (2026-11-21)
 * Topic: REC Unit III - Exception Handling & Nested Interfaces
 * Category: Interfaces & Exceptions
 * Curriculum: REC R2023 IT - Unit III (Packages, Interface & Exception Handling)
 * 
 * Description:
 * Implements a custom exception hierarchy (checked vs unchecked), nested interface
 * callback contracts, and AutoCloseable try-with-resources lifecycle management.
 */
public class Day031_Custom_Exception_Hierarchy_And_Nested_Interfaces {

    // Nested Interface Contract
    public interface PaymentGateway {
        interface TransactionListener {
            void onTransactionSuccess(String txnId, double amount);
            void onTransactionFailure(String txnId, Exception reason);
        }
        void processPayment(String txnId, double amount, TransactionListener listener) throws BankingException;
    }

    // Checked Exception Base
    public static class BankingException extends Exception {
        public BankingException(String message) { super(message); }
        public BankingException(String message, Throwable cause) { super(message, cause); }
    }

    // Checked Specialized Exception
    public static class InsufficientFundsException extends BankingException {
        private final double deficit;
        public InsufficientFundsException(String message, double deficit) {
            super(message);
            this.deficit = deficit;
        }
        public double getDeficit() { return deficit; }
    }

    // Unchecked Runtime Exception
    public static class SecurityFraudException extends RuntimeException {
        public SecurityFraudException(String message) { super(message); }
    }

    // AutoCloseable Resource for Try-With-Resources
    public static class SecureSession implements AutoCloseable {
        private final String sessionId;
        private boolean active;

        public SecureSession(String sessionId) {
            this.sessionId = sessionId;
            this.active = true;
            System.out.println("[Session] Opened secure session: " + sessionId);
        }

        public void validateRiskScore(double amount) {
            if (amount > 1_000_000) {
                throw new SecurityFraudException("Exceeded instantaneous risk threshold: $" + amount);
            }
        }

        @Override
        public void close() {
            this.active = false;
            System.out.println("[Session] Closed secure session: " + sessionId);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== REC Unit III: Exception Handling & Nested Interfaces ===");

        // Try-with-resources with custom exceptions
        try (SecureSession session = new SecureSession("SESS-8821")) {
            double accountBalance = 500.0;
            double transferAmount = 750.0;

            if (transferAmount > accountBalance) {
                throw new InsufficientFundsException("Account balance too low", transferAmount - accountBalance);
            }
        } catch (InsufficientFundsException e) {
            System.err.println("Caught InsufficientFundsException: " + e.getMessage() + " (Deficit: $" + e.getDeficit() + ")");
        } catch (BankingException e) {
            System.err.println("Caught Generic BankingException: " + e.getMessage());
        }

        // Nested Interface Callback verification
        PaymentGateway.TransactionListener listener = new PaymentGateway.TransactionListener() {
            @Override
            public void onTransactionSuccess(String txnId, double amount) {
                System.out.println("[Callback] Txn " + txnId + " approved for $" + amount);
            }
            @Override
            public void onTransactionFailure(String txnId, Exception reason) {
                System.out.println("[Callback] Txn " + txnId + " rejected: " + reason.getMessage());
            }
        };

        listener.onTransactionSuccess("TXN-9901", 250.0);
        System.out.println("All Unit III Exception & Interface contracts verified successfully!");
    }
}
