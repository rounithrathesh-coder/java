/**
 * Java Foundational Exercise - Day 75 (2026-08-20)
 * Topic: Encapsulation with Invariant Validation
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex075_Encapsulation_Bank_Account {
    static class BankAccount {
        private double balance;
        public BankAccount(double initial) { this.balance = Math.max(0, initial); }
        public void deposit(double amt) { if (amt > 0) balance += amt; }
        public double getBalance() { return balance; }
    }
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(500.0);
        acc.deposit(250.0);
        System.out.println("Balance: $" + acc.getBalance());
    }
}
