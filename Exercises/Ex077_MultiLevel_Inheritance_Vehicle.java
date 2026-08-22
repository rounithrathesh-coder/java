/**
 * Java Foundational Exercise - Day 77 (2026-08-22)
 * Topic: Multi-Level Hierarchical Inheritance
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex077_MultiLevel_Inheritance_Vehicle {
    static class Vehicle { void start() { System.out.println("Vehicle started"); } }
    static class Car extends Vehicle { void drive() { System.out.println("Car driving"); } }
    static class ElectricCar extends Car { void charge() { System.out.println("Battery charging"); } }
    public static void main(String[] args) {
        ElectricCar ev = new ElectricCar();
        ev.start(); ev.drive(); ev.charge();
    }
}
