/**
 * Java Foundational Exercise - Day 78 (2026-08-23)
 * Topic: Dynamic Method Dispatch via VTable
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex078_Method_Overriding_Polymorphism {
    static class Animal { void sound() { System.out.println("Animal sound"); } }
    static class Dog extends Animal { @Override void sound() { System.out.println("Bark"); } }
    public static void main(String[] args) {
        Animal a = new Dog(); // Dynamic dispatch
        a.sound();
    }
}
