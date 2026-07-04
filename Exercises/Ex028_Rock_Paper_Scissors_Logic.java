/**
 * Java Foundational Exercise - Day 28 (2026-07-04)
 * Topic: Game Decision Logic and State Outcomes
 * Category: Core Java & Problem Solving
 * Author: Rounith Rathesh (rounithrathesh-coder)
 */
public class Ex028_Rock_Paper_Scissors_Logic {
    public static void main(String[] args) {
        String p1 = "ROCK", p2 = "SCISSORS";
        if (p1.equals(p2)) System.out.println("Tie");
        else if ((p1.equals("ROCK") && p2.equals("SCISSORS")) ||
                 (p1.equals("PAPER") && p2.equals("ROCK")) ||
                 (p1.equals("SCISSORS") && p2.equals("PAPER"))) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
    }
}
