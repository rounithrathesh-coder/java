# ☕ 100 Days of Java: Algorithms, System Design & OOP

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 17+](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

Welcome to my daily Java coding journey! This repository features daily code implementations, algorithm solutions, system design patterns, and multithreading exercises.

---

### How to Run Locally
```bash
# Clone the repository
git clone https://github.com/rounithrathesh-coder/java.git
cd java

# Compile and run any challenge
javac daily/Day001_LRU_Cache.java
java daily.Day001_LRU_Cache
```

---

## 🎓 Academic Curriculum & Syllabus Integration (REC R2023 IT)
Starting from **Day 030**, the daily challenges systematically advance through the official **Rajalakshmi Engineering College (REC) R2023 IT Curriculum (Units I–V)**:
- **Unit I (OOP Fundamentals & Java Basics):** Bitwise operators, data types, control flow, Java 17+ pattern matching switch
- **Unit II (Classes & Inheritance):** Class hierarchies, constructor chaining (`super()`), method overriding vs dynamic dispatch, abstract classes, `Object` class contracts (`equals`, `hashCode`, `toString`)
- **Unit III (Packages, Interfaces & Exception Handling):** Package structures, access modifiers, nested interfaces, checked vs unchecked exception hierarchy, `try-with-resources`, interface default/static/private methods
- **Unit IV (I/O & Collections Framework):** Byte streams vs Character streams, Object serialization (`transient`, `serialVersionUID`), `TreeSet`/`TreeMap` Red-Black invariants, Generics bounded wildcards (`? extends`, `? super`), `PriorityQueue` heaps, `HashMap` tree-binning collision resolution
- **Unit V (Streams API, Lambdas & JDBC):** Predefined functional interfaces (`Predicate`, `Function`, `Consumer`, `Supplier`), Stream API reductions and custom collectors (`groupingBy`, `partitioningBy`), Parallel streams with `ForkJoinPool`, JDBC transaction boundaries, savepoints, batch updates, and scrollable `ResultSet`

---

## 📅 Daily Problem Log

| File | Topic | Date | Status |
| :--- | :--- | :---: | :---: |
| [`Day001_LRU_Cache.java`](daily/Day001_LRU_Cache.java) | Data Structures & System Design - LRU (Least Recently Used) Cache | 2026-11-02 | `Passing` |
| [`Day002_Two_Sum_Optimal.java`](daily/Day002_Two_Sum_Optimal.java) | Arrays, Hash Maps | 2026-11-22 | `Passing` |
| [`Day003_Valid_Parentheses_Stack.java`](daily/Day003_Valid_Parentheses_Stack.java) | Stack Data Structure | 2026-11-22 | `Passing` |
| [`Day004_Java_Streams_And_Lambdas.java`](daily/Day004_Java_Streams_And_Lambdas.java) | Functional Programming in Modern Java | 2026-11-22 | `Passing` |
| [`Day005_Producer_Consumer_BlockingQueue.java`](daily/Day005_Producer_Consumer_BlockingQueue.java) | Multithreading & Concurrent Collections | 2026-11-22 | `Passing` |
| [`Day006_Trie_Prefix_Tree.java`](daily/Day006_Trie_Prefix_Tree.java) | Tree Data Structures & Autocomplete | 2026-11-22 | `Passing` |
| [`Day007_Singleton_ThreadSafe_DoubleChecked.java`](daily/Day007_Singleton_ThreadSafe_DoubleChecked.java) | Creational Design Patterns & JVM Memory Model | 2026-11-22 | `Passing` |
| [`Day008_Binary_Search_Rotated_Array.java`](daily/Day008_Binary_Search_Rotated_Array.java) | Binary Search | 2026-11-22 | `Passing` |
| [`Day009_CompletableFuture_Async_Pipeline.java`](daily/Day009_CompletableFuture_Async_Pipeline.java) | Asynchronous Programming in Java 8+ | 2026-11-22 | `Passing` |
| [`Day010_Observer_Pattern_EventBus.java`](daily/Day010_Observer_Pattern_EventBus.java) | Event-driven Architecture & Behavioral Design Patterns | 2026-11-22 | `Passing` |
| [`Day011_Topological_Sort.java`](daily/Day011_Topological_Sort.java) | Graphs - Kahn's Algorithm for Topological Sort | 2026-11-08 | `Passing` |
| [`Day012_Builder_Pattern.java`](daily/Day012_Builder_Pattern.java) | Design Patterns - Fluent Builder Pattern | 2026-11-09 | `Passing` |
| [`Day013_Custom_Generic_LinkedList.java`](daily/Day013_Custom_Generic_LinkedList.java) | Data Structures - Generic Singly Linked List | 2026-11-10 | `Passing` |
| [`Day014_Sliding_Window_Maximum.java`](daily/Day014_Sliding_Window_Maximum.java) | Data Structures - Sliding Window Maximum with Deque | 2026-11-10 | `Passing` |
| [`Day015_Longest_Palindromic_Substring.java`](daily/Day015_Longest_Palindromic_Substring.java) | Dynamic Programming / String Algorithms - Longest Palindromic Substring | 2026-11-11 | `Passing` |
| [`Day016_Dijkstra_Shortest_Path.java`](daily/Day016_Dijkstra_Shortest_Path.java) | Graph Algorithms - Dijkstra's Single-Source Shortest Path | 2026-11-11 | `Passing` |
| [`Day017_Kth_Largest_Element.java`](daily/Day017_Kth_Largest_Element.java) | Heap / Quickselect - Kth Largest Element | 2026-11-11 | `Passing` |
| [`Day018_Merge_Intervals.java`](daily/Day018_Merge_Intervals.java) | Interval Scheduling - Merge Overlapping Intervals & Insert Interval | 2026-11-12 | `Passing` |
| [`Day019_Token_Bucket_Rate_Limiter.java`](daily/Day019_Token_Bucket_Rate_Limiter.java) | System Design & Concurrency - Token Bucket Rate Limiter | 2026-11-12 | `Passing` |
| [`Day020_Disjoint_Set_Union.java`](daily/Day020_Disjoint_Set_Union.java) | Data Structures - Disjoint Set Union (DSU) with Path Compression & Union by Rank | 2026-11-13 | `Passing` |
| [`Day021_Consistent_Hashing_Ring.java`](daily/Day021_Consistent_Hashing_Ring.java) | Distributed Systems - Consistent Hashing Ring with Virtual Nodes | 2026-11-13 | `Passing` |
| [`Day022_Lock_Free_Stack_Treiber.java`](daily/Day022_Lock_Free_Stack_Treiber.java) | Concurrency - Lock-Free Treiber Stack with AtomicReference | 2026-11-14 | `Passing` |
| [`Day023_Segment_Tree_Range_Query.java`](daily/Day023_Segment_Tree_Range_Query.java) | Trees & Range Queries - Segment Tree with Point Updates | 2026-11-15 | `Passing` |
| [`Day024_LRU_LFU_Hybrid_Cache.java`](daily/Day024_LRU_LFU_Hybrid_Cache.java) | System Design - LFU (Least Frequently Used) Cache with Doubly Linked Lists | 2026-11-15 | `Passing` |
| [`Day025_A_Star_Pathfinding.java`](daily/Day025_A_Star_Pathfinding.java) | Graph Algorithms - A* Shortest Path Search with Heuristics | 2026-11-16 | `Passing` |
| [`Day026_Read_Write_Lock_Implementation.java`](daily/Day026_Read_Write_Lock_Implementation.java) | Multithreading - Custom Fair ReadWriteLock with Reentrancy | 2026-11-17 | `Passing` |
| [`Day027_Thread_Pool_Executor_Custom.java`](daily/Day027_Thread_Pool_Executor_Custom.java) | Multithreading - Custom Thread Pool Executor & Worker Task Queue | 2026-11-17 | `Passing` |
| [`Day028_Bloom_Filter_Probabilistic.java`](daily/Day028_Bloom_Filter_Probabilistic.java) | Probabilistic Data Structures - Bloom Filter with Optimal Hash Sizing | 2026-11-18 | `Passing` |
| [`Day029_Skip_List_Concurrent.java`](daily/Day029_Skip_List_Concurrent.java) | Data Structures - Concurrent SkipList Map with Probabilistic Balancing | 2026-11-19 | `Passing` |
| [`Day030_Inheritance_Abstract_Classes_And_Object_Contract.java`](daily/Day030_Inheritance_Abstract_Classes_And_Object_Contract.java) | REC Unit II - Classes, Abstract Classes & Object Methods (equals/hashCode) | 2026-11-20 | `Passing` |
| [`Day031_Custom_Exception_Hierarchy_And_Nested_Interfaces.java`](daily/Day031_Custom_Exception_Hierarchy_And_Nested_Interfaces.java) | REC Unit III - Exception Handling & Nested Interfaces | 2026-11-21 | `Passing` |

---

## 📂 Repository Structure
- `Basics/`: Java fundamentals, datatypes, operators, conditionals
- `Loops/`: Loops, iterations, logic puzzles
- `OOPS/`: Object-Oriented Programming (Classes, Inheritance, Polymorphism, Encapsulation)
- `daily/`: Daily code challenges, advanced algorithms, and design patterns

---
*Maintained by [@rounithrathesh-coder](https://github.com/rounithrathesh-coder)*
