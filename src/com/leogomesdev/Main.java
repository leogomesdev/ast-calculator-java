package com.leogomesdev;

import com.leogomesdev.services.CalculatorService;

import java.util.Scanner;

/**
 * The class that is executed first.
 * This class just allows the user to input some expression and calls calculatorService to evaluate the expression.
 */
public class Main {
    private static CalculatorService calculatorService = new CalculatorService();
    private static Scanner input = new Scanner(System.in);
    private static Integer executionCounter = 0;

    public static void main(String[] args) {
        startFromConsole();
    }

    /**
     * Function that allows console interaction with the user
     */
    private static void startFromConsole() {
        executionCounter++;
        System.out.println("Type your expression and press [ENTER]:");
        String expression = input.nextLine();
        try {
            double response = calculatorService.calculate(expression);
            System.out.format("The answer is: %n%.1f%n%n", response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Integer EXECUTION_LIMIT = 10;
        if (executionCounter >= EXECUTION_LIMIT) {
            System.out.format("Ending after %s executions, just to avoid infinity loop", EXECUTION_LIMIT);
            return;
        }
        startFromConsole();
    }
}
