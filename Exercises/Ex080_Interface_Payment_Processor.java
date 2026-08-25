/**
 * Java Foundational Exercise - Day 80 (2026-08-25)
 * Topic: Multiple Interface Implementation Contracts
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex080_Interface_Payment_Processor {
    interface Payable { void pay(double amt); }
    static class CardPayment implements Payable {
        @Override public void pay(double amt) { System.out.println("Paid $" + amt + " with card"); }
    }
    public static void main(String[] args) {
        Payable p = new CardPayment();
        p.pay(120.0);
    }
}
