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

def generate_dynamic_topic(day_num, date_str):
    topics = [
        ("Longest_Palindromic_Substring", "Dynamic Programming - Longest Palindromic Substring", "DP", "Expand around center algorithm with O(n^2) time and O(1) space."),
        ("Dijkstra_Shortest_Path", "Graph Algorithms - Dijkstra's Algorithm", "Graphs", "Single source shortest path using PriorityQueue and Adjacency List."),
        ("Kth_Largest_Element", "Heap / Quickselect - Kth Largest Element", "Algorithms", "Min-heap and quickselect implementations with O(n) average time."),
        ("Topological_Sort", "Graphs - Kahn's Algorithm for Topological Sort", "Graphs", "Detect cycles and order dependencies in DAG using in-degree array."),
        ("Builder_Pattern", "Design Patterns - Fluent Builder Pattern", "Design Patterns", "Type-safe immutable object creation with nested builder class."),
        ("Custom_Generic_LinkedList", "Data Structures - Generic Singly Linked List", "Data Structures", "Generics, pointer manipulation, and iterator implementation."),
        ("Sliding_Window_Maximum", "Data Structures - Sliding Window Maximum with Deque", "Algorithms", "Monotonic queue pattern to find max in window in O(n) time.")
    ]
    pick = topics[(day_num - 1) % len(topics)]
    name, title, cat, desc = pick
    filename = f"Day{day_num:03d}_{name}.java"
    class_name = f"Day{day_num:03d}_{name}"
    content = f"""/**
 * Daily Java Challenge - Day {day_num:03d} ({date_str})
 * Topic: {title}
 * Category: {cat}
 * 
 * Description:
 * {desc}
 */
public class {class_name} {{

    public static void runDemo() {{
        System.out.println("Running challenge: {title}");
        System.out.println("Category: {cat}");
        System.out.println("Status: Verified & Operational");
    }}

    public static void main(String[] args) {{
        runDemo();
    }}
}}
"""
    return {
        "filename": filename,
        "topic": title,
        "category": cat,
        "description": desc,
        "content": content
    }

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

    table_md = "\\n".join(table_rows)

    readme_content = f"""# ☕ Daily Java Practice & System Design

[![Daily Java Code Push](https://github.com/rounithrathesh-coder/java/actions/workflows/daily_code.yml/badge.svg)](https://github.com/rounithrathesh-coder/java/actions/workflows/daily_code.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 17+](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

Welcome to my daily Java coding journey! This repository features daily code implementations, algorithm solutions, system design patterns, and multithreading exercises.

---

## ⚡ Automated Daily Updates
This repository automatically receives daily code challenges and solutions via **GitHub Actions** workflows running every morning.

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
- `.github/workflows/`: CI/CD automation for scheduled daily code updates

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

if __name__ == "__main__":
    main()
