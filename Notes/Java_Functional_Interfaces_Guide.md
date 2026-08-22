# Predefined Functional Interfaces (`java.util.function`)

| Interface | Method Signature | Purpose |
|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | Evaluates a condition on an argument |
| `Consumer<T>` | `void accept(T t)` | Consumes an argument without returning a value |
| `Function<T, R>` | `R apply(T t)` | Transforms an input of type T to output of type R |
| `Supplier<T>` | `T get()` | Produces a value of type T with zero arguments |
| `UnaryOperator<T>` | `T apply(T t)` | Specialized `Function<T, T>` where types match |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | Specialized `BiFunction<T, T, T>` |


<!-- Authored: 2026-08-19 17:21:30 -->

<!-- Section Revision: 2026-09-02 10:07:57 -->

<!-- Section Revision: 2026-09-02 14:50:45 -->

<!-- Section Revision: 2026-09-05 22:49:09 -->

<!-- Section Revision: 2026-09-12 08:02:52 -->

<!-- Section Revision: 2026-10-04 15:17:28 -->

<!-- Section Revision: 2026-10-08 15:01:13 -->

<!-- Section Revision: 2026-10-28 22:26:04 -->

<!-- Topic Section Update: 2026-06-01 09:02:47 -->

<!-- Topic Section Update: 2026-07-23 15:54:13 -->

<!-- Topic Section Update: 2026-07-25 19:48:20 -->

<!-- Topic Section Update: 2026-07-28 09:54:46 -->

<!-- Topic Section Update: 2026-08-22 08:56:21 -->
