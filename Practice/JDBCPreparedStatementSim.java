/**
 * Practice Implementation: JDBC PreparedStatement Parameter Binding Pattern
 * Revision: #214 | Date: 2026-10-21 15:52:26
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class JDBCPreparedStatementSim {
    static class MockPreparedStatement {
        private String sql;
        MockPreparedStatement(String s) { this.sql = s; }
        public void setInt(int idx, int val) { sql = sql.replaceFirst("\\?", String.valueOf(val)); }
        public void setString(int idx, String val) { sql = sql.replaceFirst("\\?", "'" + val + "'"); }
        public String getResolvedSql() { return sql; }
    }
    public static void main(String[] args) {
        MockPreparedStatement ps = new MockPreparedStatement("SELECT * FROM users WHERE id = ? AND role = ?");
        ps.setInt(1, 42);
        ps.setString(2, "admin");
        System.out.println("Bound SQL: " + ps.getResolvedSql());
    }
}
