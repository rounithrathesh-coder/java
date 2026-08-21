/**
 * Java Foundational Exercise - Day 76 (2026-08-21)
 * Topic: Single Inheritance and Super Constructor
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex076_Single_Inheritance_Person_Student {
    static class Person {
        String name;
        Person(String name) { this.name = name; }
    }
    static class Student extends Person {
        int rollNo;
        Student(String name, int rollNo) { super(name); this.rollNo = rollNo; }
    }
    public static void main(String[] args) {
        Student s = new Student("Alex", 202);
        System.out.println("Name: " + s.name + ", Roll: " + s.rollNo);
    }
}
