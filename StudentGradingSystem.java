import java.util.Scanner;

public class StudentGradingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Number of subjects
        int numSubjects = 5;  // You can change the number of subjects as needed
        
        // Array to store marks for each subject
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        
        // Input marks for each subject
        System.out.println("Enter marks obtained in each subject (out of 100):");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];  // Summing up the marks
        }
        
        // Calculate average percentage
        double averagePercentage = (totalMarks / (double)(numSubjects * 100)) * 100;
        
        // Grade calculation based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        
        // Display results
        System.out.println("\nTotal Marks: " + totalMarks + "/" + (numSubjects * 100));
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}
