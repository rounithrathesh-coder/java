# Java Exception Handling Architecture

## Hierarchy
```
Throwable
 ├── Error (Unrecoverable JVM issues: OutOfMemoryError, StackOverflowError)
 └── Exception
      ├── RuntimeException (Unchecked: NullPointerException, IndexOutOfBoundsException)
      └── Checked Exceptions (Must be declared or handled: IOException, SQLException)
```

## Best Practices
1. **Never catch `Throwable` or `Error`**: JVM-level errors should terminate the process cleanly.
2. **Always preserve root cause**: Use `new CustomException("message", originalCause)`.
3. **Prefer try-with-resources**: Prevents socket/file descriptor leaks.


<!-- Authored: 2026-09-02 08:20:17 -->

<!-- Section Revision: 2026-09-02 11:42:31 -->

<!-- Section Revision: 2026-09-13 21:43:04 -->

<!-- Section Revision: 2026-09-27 10:13:12 -->

<!-- Section Revision: 2026-10-16 20:28:34 -->

<!-- Topic Section Update: 2026-08-30 11:45:49 -->
