/**
 * Java Foundational Exercise - Day 74 (2026-08-19)
 * Topic: Constructor Chaining and Instance Initialization
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex074_Simple_Class_And_Constructors {
    static class Student {
        String name;
        int id;
        Student(int id, String name) { this.id = id; this.name = name; }
    }
    public static void main(String[] args) {
        Student s = new Student(101, "Rounith");
        System.out.println("Student ID: " + s.id + ", Name: " + s.name);
    }
}
