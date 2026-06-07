/**
 * Practice Implementation: Automatic Resource Management with AutoCloseable
 * Revision: #3 | Date: 2026-06-07 09:56:29
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class TryWithResourcesAutoCloseable {
    static class CustomResource implements AutoCloseable {
        public void execute() { System.out.println("Resource executing..."); }
        @Override
        public void close() { System.out.println("Resource closed safely."); }
    }
    public static void main(String[] args) {
        try (CustomResource res = new CustomResource()) {
            res.execute();
        }
    }
}
