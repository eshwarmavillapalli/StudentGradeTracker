import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect student names
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();  // Consume the newline
        String[] studentNames = new String[numStudents];
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            studentNames[i] = scanner.nextLine();
        }

        // Collect scores for each student
        System.out.print("Enter the number of assignments: ");
        int numAssignments = scanner.nextInt();
        double[][] studentScores = new double[numStudents][numAssignments];
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter scores for " + studentNames[i] + ":");
            for (int j = 0; j < numAssignments; j++) {
                System.out.print("Assignment " + (j + 1) + " score: ");
                studentScores[i][j] = scanner.nextDouble();
            }
        }

        // Convert columns to rows for assignment analysis
        List<List<Double>> assignmentScores = colsToRows(studentScores);

        // Print student results
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n" + studentNames[i]);
            System.out.println("Highest score = " + max(studentScores[i]));
            System.out.println("Lowest score = " + min(studentScores[i]));
            System.out.println("Mean = " + mean(studentScores[i]) + " Grade: " + gradeLetter(mean(studentScores[i])));
            System.out.println("Mean (lowest dropped) = " + meanLowDrop(studentScores[i]));
            System.out.println("-------------------------------------");
        }

        // Print assignment results
        System.out.println("\n\nAssignment Analysis:");
        for (int j = 1; j <= numAssignments; j++) {
            double[] assignmentScoresArr = assignmentScores.get(j - 1).stream().mapToDouble(Double::doubleValue).toArray();
            System.out.println("\nAssignment " + j + ":");
            System.out.println("Highest score = " + max(assignmentScoresArr));
            System.out.println("Lowest score = " + min(assignmentScoresArr));
            System.out.println("Mean = " + mean(assignmentScoresArr) + " Grade: " + gradeLetter(mean(assignmentScoresArr)));
            System.out.println("Mean (lowest dropped) = " + meanLowDrop(assignmentScoresArr));
            System.out.println("-------------------------------------");
        }

        scanner.close();
    }

    public static double max(double[] numbers) {
        double result = numbers[0];
        for (double number : numbers) {
            if (number > result) {
                result = number;
            }
        }
        return result;
    }

    public static double min(double[] numbers) {
        double result = numbers[0];
        for (double number : numbers) {
            if (number < result) {
                result = number;
            }
        }
        return result;
    }

    public static double mean(double[] numbers) {
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    public static double meanLowDrop(double[] numbers) {
        double lowestGrade = min(numbers);
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return (sum - lowestGrade) / (numbers.length - 1);
    }

    public static char gradeLetter(double mean) {
        if (mean >= 90) return 'A';
        else if (mean >= 80) return 'B';
        else if (mean >= 70) return 'C';
        else if (mean >= 65) return 'D';
        else return 'F';
    }

    public static List<List<Double>> colsToRows(double[][] studentGrades) {
        List<List<Double>> rows = new ArrayList<>();
        for (int i = 0; i < studentGrades[0].length; i++) {
            List<Double> temp = new ArrayList<>();
            for (double[] studentGrade : studentGrades) {
                temp.add(studentGrade[i]);
            }
            rows.add(temp);
        }
        return rows;
    }
}