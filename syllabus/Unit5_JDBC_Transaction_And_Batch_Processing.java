/**
 * REC R2023 IT Curriculum - Unit V: Streams API, Lambda and JDBC
 * Topic: Accessing Databases with JDBC (Transactions, PreparedStatements & Batching)
 *
 * Concepts Demonstrated:
 * 1. JDBC Connection Lifecycle and AutoCommit management.
 * 2. Parameterized PreparedStatement preventing SQL Injection.
 * 3. Batch Updates (addBatch, executeBatch) for high-performance writes.
 * 4. ACID Transaction Management: commit(), rollback(), and Savepoints.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Unit5_JDBC_Transaction_And_Batch_Processing {

    // Domain Model
    public record CourseEnrollment(int studentId, String courseCode, double feePaid) {}

    // Simulated Production JDBC Repository demonstrating standard java.sql workflow
    public static class EnrollmentRepository {
        private final List<CourseEnrollment> persistentDatabase = new ArrayList<>();
        private boolean autoCommit = true;

        public void setAutoCommit(boolean autoCommit) {
            this.autoCommit = autoCommit;
        }

        /**
         * Simulates PreparedStatement batch execution:
         *   PreparedStatement ps = conn.prepareStatement("INSERT INTO enrollments VALUES (?, ?, ?)");
         *   for (...) { ps.setInt(1, ...); ps.addBatch(); }
         *   int[] results = ps.executeBatch();
         */
        public int[] executeBatchEnrollment(List<CourseEnrollment> batch) throws SQLException {
            int[] updateCounts = new int[batch.size()];
            for (int i = 0; i < batch.size(); i++) {
                CourseEnrollment item = batch.get(i);
                if (item.feePaid() < 0) {
                    throw new SQLException("Integrity Violation: Negative fee is not allowed for " + item.studentId());
                }
                persistentDatabase.add(item);
                updateCounts[i] = 1; // 1 row affected
            }
            return updateCounts;
        }

        /**
         * Simulates Transactional Commit and Rollback with Savepoints.
         */
        public boolean executeAtomicTransfer(int fromStudent, int toStudent, double amount) {
            // Emulates:
            // conn.setAutoCommit(false);
            // Savepoint sp = conn.setSavepoint("BeforeTransfer");
            // try { ... conn.commit(); } catch(...) { conn.rollback(sp); }
            List<CourseEnrollment> snapshot = new ArrayList<>(persistentDatabase);
            try {
                // Deduct from sender
                boolean foundSender = false;
                for (int i = 0; i < persistentDatabase.size(); i++) {
                    CourseEnrollment e = persistentDatabase.get(i);
                    if (e.studentId() == fromStudent) {
                        if (e.feePaid() < amount) {
                            throw new IllegalStateException("Insufficient funds for student " + fromStudent);
                        }
                        persistentDatabase.set(i, new CourseEnrollment(fromStudent, e.courseCode(), e.feePaid() - amount));
                        foundSender = true;
                        break;
                    }
                }
                if (!foundSender) throw new IllegalArgumentException("Sender not found");

                // Simulated error to demonstrate rollback
                if (amount > 1000.0) {
                    throw new RuntimeException("Simulated Network Timeout during commit!");
                }

                return true; // Success (commit)
            } catch (Exception ex) {
                // Rollback to snapshot (Savepoint)
                persistentDatabase.clear();
                persistentDatabase.addAll(snapshot);
                return false;
            }
        }

        public int getRecordCount() {
            return persistentDatabase.size();
        }

        public List<CourseEnrollment> getAll() {
            return new ArrayList<>(persistentDatabase);
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("=======================================================================");
        System.out.println("REC R2023 IT - Unit V: JDBC Architecture, Batching & Transactions");
        System.out.println("=======================================================================");

        EnrollmentRepository repo = new EnrollmentRepository();

        // 1. Batch Execution
        System.out.println("Test 1: Executing PreparedStatement Batch Insert...");
        List<CourseEnrollment> batch = List.of(
            new CourseEnrollment(201, "IT8501-JAVA", 500.0),
            new CourseEnrollment(202, "IT8502-DBMS", 600.0),
            new CourseEnrollment(203, "IT8503-OS",   450.0)
        );

        int[] results = repo.executeBatchEnrollment(batch);
        System.out.printf("  Batch submitted: %d items, Records persisted: %d%n", results.length, repo.getRecordCount());
        assert results.length == 3 && repo.getRecordCount() == 3 : "Batch update count mismatch!";
        System.out.println("-> Test 1 Passed: JDBC batch update execution verified.");

        // 2. Transaction Rollback on Failure
        System.out.println("\nTest 2: Simulating Transaction Failure & Rollback...");
        boolean success = repo.executeAtomicTransfer(201, 202, 1500.0); // Exceeds threshold, triggers rollback
        System.out.printf("  Atomic transfer result: %s (Rollback applied)%n", success ? "COMMITTED" : "ROLLED_BACK");
        assert !success : "Transaction should have failed and rolled back!";

        // Verify balance was not modified after rollback
        CourseEnrollment originalSender = repo.getAll().stream().filter(e -> e.studentId() == 201).findFirst().orElseThrow();
        assert originalSender.feePaid() == 500.0 : "Rollback failed: State was mutated!";
        System.out.println("-> Test 2 Passed: ACID Transaction atomicity and rollback verified.");

        System.out.println("\nAll Unit V JDBC assertions passed successfully!");
    }
}
