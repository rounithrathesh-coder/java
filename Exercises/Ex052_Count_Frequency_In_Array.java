/**
 * Java Foundational Exercise - Day 52 (2026-07-28)
 * Topic: Occurrence Counting using Hash Frequency
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex052_Count_Frequency_In_Array {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 1, 4, 2};
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        for (int x : arr) freq.put(x, freq.getOrDefault(x, 0) + 1);
        System.out.println("Frequencies: " + freq);
    }
}
