/**
 * Practice Implementation: Custom Checked Exception Hierarchy and Propagation
 * Revision: #200 | Date: 2026-10-16 20:23:02
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class CustomCheckedExceptionDemo {
    static class InsufficientBalanceException extends Exception {
        public InsufficientBalanceException(String msg) { super(msg); }
    }
    static class Account {
        private int balance = 500;
        public void withdraw(int amt) throws InsufficientBalanceException {
            if (amt > balance) throw new InsufficientBalanceException("Insufficient funds: " + balance);
            balance -= amt;
        }
    }
    public static void main(String[] args) {
        Account acc = new Account();
        try {
            acc.withdraw(1000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
