/**
 * Practice Implementation: JDBC Connection Transaction Commit and Savepoint Rollback
 * Revision: #77 | Date: 2026-09-01 11:19:57
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class JDBCTransactionSavepointSim {
    static class MockConnection {
        private boolean autoCommit = true;
        public void setAutoCommit(boolean ac) { this.autoCommit = ac; }
        public void commit() { System.out.println("Transaction committed."); }
        public void rollback() { System.out.println("Transaction rolled back to savepoint."); }
    }
    public static void main(String[] args) {
        MockConnection conn = new MockConnection();
        conn.setAutoCommit(false);
        System.out.println("Transaction started.");
        conn.commit();
    }
}
