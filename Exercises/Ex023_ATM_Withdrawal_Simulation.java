/**
 * Java Foundational Exercise - Day 23 (2026-06-29)
 * Topic: Banking Validation and Currency Dispenser
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex023_ATM_Withdrawal_Simulation {
    public static void main(String[] args) {
        int balance = 5000, requested = 1500;
        if (requested <= balance && requested % 100 == 0) {
            balance -= requested;
            System.out.println("Dispensed $" + requested + ", Remaining: $" + balance);
        } else {
            System.out.println("Invalid amount");
        }
    }
}
