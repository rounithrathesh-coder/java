import java.util.Scanner;

public class atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pin;

        do {
            System.out.print("Enter the pin: ");
            pin = sc.nextInt();

            if (pin != 1111) {
                System.out.println("Incorrect pin, Enter the pin: ");
            }
        } while (pin != 1111);

        System.out.println("Log in successful");
        sc.close();
    }
}
