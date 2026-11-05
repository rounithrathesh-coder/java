/**
 * Daily Java Challenge - Thread-Safe Singleton
 * Topic: Creational Design Patterns & JVM Memory Model
 */
public class Day007_Singleton_ThreadSafe_DoubleChecked {

    public static class DatabaseConnectionManager {
        // volatile ensures changes made by one thread are visible to others immediately
        // and prevents instruction reordering by the compiler/JVM.
        private static volatile DatabaseConnectionManager instance;
        private final String connectionString;

        private DatabaseConnectionManager() {
            this.connectionString = "jdbc:postgresql://localhost:5432/main_db";
            System.out.println("DatabaseConnectionManager initialized.");
        }

        public static DatabaseConnectionManager getInstance() {
            if (instance == null) {
                synchronized (DatabaseConnectionManager.class) {
                    if (instance == null) {
                        instance = new DatabaseConnectionManager();
                    }
                }
            }
            return instance;
        }

        public void executeQuery(String query) {
            System.out.println("Executing on [" + connectionString + "]: " + query);
        }
    }

    public static void main(String[] args) {
        DatabaseConnectionManager mgr1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager mgr2 = DatabaseConnectionManager.getInstance();

        System.out.println("Same instance reference? " + (mgr1 == mgr2));
        mgr1.executeQuery("SELECT * FROM users;");
    }
}
