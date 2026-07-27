/**
 * Practice Implementation: Functional Null-Safe Workflows with java.util.Optional
 * Summer Revision: #116 | Date: 2026-07-27 14:14:48
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.Optional;
public class OptionalNullSafeWorkflows {
    static class User { String email; User(String e) { email = e; } }
    public static void main(String[] args) {
        User u = new User("rounith@example.com");
        String domain = Optional.ofNullable(u)
            .map(user -> user.email)
            .filter(e -> e.contains("@"))
            .map(e -> e.substring(e.indexOf("@") + 1))
            .orElse("unknown");
        System.out.println("Extracted Domain: " + domain);
    }
}
