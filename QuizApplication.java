import java.util.*;
import java.util.concurrent.*;

public class QuizApplication {

    // Inner class to represent each quiz question
    static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        // Constructor to initialize the question, options, and correct answer index
        public Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        // Method to display the question and options
        public void displayQuestion(int questionNumber) {
            System.out.println("\nQuestion " + questionNumber + ": " + question);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }

        // Method to check if the selected answer is correct
        public boolean isCorrectAnswer(int answer) {
            return answer == correctAnswerIndex;
        }
    }

    // Main method to run the quiz application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Quiz questions, options, and correct answer index
        Question[] questions = {
            new Question("Which number is the largest?", new String[]{"25", "50", "100", "75"}, 3),
            new Question("What is the capital of India?", new String[]{"New Delhi", "Mumbai", "Kolkata", "Chennai"}, 1),
            new Question("What is the capital of Gujarat?", new String[]{"Vadodara", "Ahmedabad", "Surat", "Gandhinagar"}, 2),
            new Question("What is the full form of MI in IPL?", new String[]{"Mumbai International", "Mumbai Indians", "Maharashtra Indians", "Magical Indians"}, 2),
            new Question("How many IPL trophies have Mumbai Indians won?", new String[]{"3", "4", "5", "6"}, 3)
        };

        int score = 0;
        int totalQuestions = questions.length;
        Map<Integer, Boolean> answerStatus = new HashMap<>();
        
        // Set up the timer for each question
        ExecutorService executor = Executors.newFixedThreadPool(1);
        long timeLimit = 15;  // Timer set for 15 seconds for each question

        // Loop through each question
        for (int i = 0; i < totalQuestions; i++) {
            questions[i].displayQuestion(i + 1);
            
            // Start the timer for this question
            Timer timer = new Timer(timeLimit);
            Future<Integer> answerFuture = executor.submit(() -> {
                // Wait for the user to input an answer within the time limit
                return getUserAnswer(scanner);
            });

            try {
                // Get the user's answer or time out after timeLimit seconds
                Integer answer = answerFuture.get(timeLimit, TimeUnit.SECONDS);

                // Check if the answer is correct
                if (questions[i].isCorrectAnswer(answer)) {
                    System.out.println("Correct!");
                    score++;
                    answerStatus.put(i + 1, true);
                } else {
                    System.out.println("Incorrect!");
                    answerStatus.put(i + 1, false);
                }
            } catch (TimeoutException e) {
                System.out.println("\nTime's up! You did not answer in time.");
                answerStatus.put(i + 1, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Show the final score and summary
        System.out.println("\nQuiz Complete!");
        System.out.println("Your final score is: " + score + "/" + totalQuestions);
        showResultSummary(answerStatus, totalQuestions);

        scanner.close();
        executor.shutdown();
    }

    // Method to get the user's answer
    private static int getUserAnswer(Scanner scanner) {
        System.out.print("Enter the number of your answer: ");
        return scanner.nextInt();
    }

    // Method to display the result summary
    private static void showResultSummary(Map<Integer, Boolean> answerStatus, int totalQuestions) {
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        
        for (Map.Entry<Integer, Boolean> entry : answerStatus.entrySet()) {
            if (entry.getValue()) {
                correctAnswers++;
            } else {
                incorrectAnswers++;
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
        System.out.println("Total Questions: " + totalQuestions);
    }

    // Inner class to handle the timer for each question
    static class Timer {
        private final long timeLimit;

        public Timer(long timeLimit) {
            this.timeLimit = timeLimit;
        }

        // Starts the countdown timer
        public void start() {
            for (long i = timeLimit; i >= 0; i--) {
                System.out.print("\rTime remaining: " + i + " seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nTime's up!");
        }
    }
}
