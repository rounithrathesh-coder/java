/**
 * Java Foundational Exercise - Day 83 (2026-08-28)
 * Topic: Fast Hash Table Key-Value Retrieval
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex083_HashMap_Key_Value_Storage {
    public static void main(String[] args) {
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        map.put("Alice", 92); map.put("Bob", 85);
        System.out.println("Alice's score: " + map.get("Alice"));
    }
}
