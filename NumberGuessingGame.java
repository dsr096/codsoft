import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 0;
        int score = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        
        while (true) {
            rounds++;
            int attempts = 0;
            int maxAttempts = 10;  // Limit of attempts
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            boolean guessedCorrectly = false;
            
            System.out.println("\nRound " + rounds + " - Guess the number between 1 and 100:");
            
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score += (maxAttempts - attempts + 1);  // Add points based on attempts left
                    System.out.println("It took you " + attempts + " attempts.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all " + maxAttempts + " attempts. The correct number was " + numberToGuess);
            }
            
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgain = scanner.next();
            
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }
        
        System.out.println("\nGame Over! Your total score is: " + score);
        scanner.close();
    }
}
