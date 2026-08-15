/**
 * Practice Implementation: Automatic Resource Management with AutoCloseable
 * Summer Revision: #159 | Date: 2026-08-15 16:27:48
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
