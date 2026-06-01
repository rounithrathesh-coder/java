/**
 * Practice Implementation: Automatic Resource Management with AutoCloseable
 * Summer Revision: #2 | Date: 2026-06-01 09:18:43
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
