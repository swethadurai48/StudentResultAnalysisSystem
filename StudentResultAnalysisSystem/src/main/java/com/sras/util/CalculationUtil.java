package com.sras.util;

public class CalculationUtil {
    
    public static double calculatePercentage(double totalMarks, double maxMarks) {
        if (maxMarks == 0) return 0.0;
        return (totalMarks / maxMarks) * 100.0;
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public static String determineStatus(double percentage) {
        return percentage >= 50 ? "Pass" : "Fail";
    }
}
