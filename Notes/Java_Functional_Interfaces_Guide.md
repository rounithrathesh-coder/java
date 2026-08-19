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
