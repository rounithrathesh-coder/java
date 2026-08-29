/**
 * Java Foundational Exercise - Day 84 (2026-08-29)
 * Topic: Functional Comparator Lambda Expression
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex084_Lambda_Comparator_Sorting {
    public static void main(String[] args) {
        java.util.List<Integer> nums = new java.util.ArrayList<>(java.util.List.of(42, 12, 88, 3));
        nums.sort((a, b) -> Integer.compare(a, b));
        System.out.println("Sorted with lambda: " + nums);
    }
}
