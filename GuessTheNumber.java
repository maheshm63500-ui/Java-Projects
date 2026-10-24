import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int bestScore = Integer.MAX_VALUE;
        String playAgain;

        System.out.println("=================================");
        System.out.println("      GUESS THE NUMBER GAME      ");
        System.out.println("=================================");

        do {
            System.out.println("\nChoose difficulty:");
            System.out.println("1. Easy   (1-50,  10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard   (1-500, 9 attempts)");
            System.out.print("Enter choice (1-3): ");

            int choice = readInt(sc);
            int max, maxAttempts;
            switch (choice) {
                case 1:  max = 50;  maxAttempts = 10; break;
                case 3:  max = 500; maxAttempts = 9;  break;
                default: max = 100; maxAttempts = 7;  break;
            }

            int secret = rand.nextInt(max) + 1;
            int attempts = 0;
            boolean won = false;

            System.out.println("\nI'm thinking of a number between 1 and " + max
                    + ". You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + " - Your guess: ");
                int guess = readInt(sc);

                if (guess < 1 || guess > max) {
                    System.out.println("Please enter a number between 1 and " + max + ".");
                    continue;
                }

                attempts++;

                if (guess == secret) {
                    won = true;
                    break;
                } else if (guess < secret) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                if (Math.abs(guess - secret) <= 5) {
                    System.out.println("Hint: You're very close!");
                }
            }

            if (won) {
                System.out.println("\nCorrect! You guessed it in " + attempts + " attempt(s).");
                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("New best score!");
                }
            } else {
                System.out.println("\nGame over! The number was " + secret + ".");
            }

            if (bestScore != Integer.MAX_VALUE) {
                System.out.println("Best score so far: " + bestScore + " attempt(s)");
            }

            System.out.print("\nPlay again? (y/n): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thanks for playing! Goodbye.");
        sc.close();
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.print("That's not a number. Try again: ");
            }
        }
    }
}