# Java 8 Streams API Cheatsheet

## Pipeline Mechanics
A Stream pipeline consists of three phases:
1. **Source**: Collection (`list.stream()`), Array (`Arrays.stream(arr)`), or Generator (`Stream.iterate()`).
2. **Intermediate Operations (Lazy)**:
   - `filter(Predicate<T>)`
   - `map(Function<T, R>)`
   - `flatMap(Function<T, Stream<R>>)`
   - `distinct()`, `sorted(Comparator<T>)`, `peek(Consumer<T>)`
   - `limit(long maxSize)`, `skip(long n)`
3. **Terminal Operations (Eager)**:
   - `forEach(Consumer<T>)`
   - `collect(Collector<T, A, R>)`
   - `reduce(BinaryOperator<T>)`
   - `count()`, `min()`, `max()`, `anyMatch()`, `allMatch()`, `noneMatch()`, `findFirst()`


<!-- Authored: 2026-06-25 22:22:42 -->

<!-- Section Revision: 2026-08-15 10:28:12 -->

<!-- Section Revision: 2026-09-09 22:52:16 -->

<!-- Section Revision: 2026-09-11 14:40:18 -->

<!-- Section Revision: 2026-10-07 11:12:40 -->

<!-- Topic Section Update: 2026-06-12 09:49:22 -->

<!-- Topic Section Update: 2026-06-16 14:28:57 -->

<!-- Topic Section Update: 2026-07-13 17:20:25 -->
