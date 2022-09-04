package com.example.cli;

import java.util.Scanner;

public class CommandLineInputControl {
    private CommandLineInterface userInterface;

    public CommandLineInputControl(CommandLineInterface cli) {
        this.userInterface = cli;
    }

    private final static Scanner SCANNER = new Scanner(System.in);

    public int[][] inputParkingSlotsDimensions(int numOfParkingSlots) {
        int[][] dimensions = new int[numOfParkingSlots][2];
        for (int i = 0; i < numOfParkingSlots; i++) {
            int width = this.inputPositiveInt("Enter the width of parking slot " + i+1 + ": ");
            int depth = this.inputPositiveInt("Enter the depth of parking slot " + i+1 + ": ");
            dimensions[i] = new int[]{width, depth};
        }
        return dimensions;
    }

    public int inputPositiveInt(String prompt) {
        while (true) {
            try {
                this.userInterface.prompt(prompt);
                int res = Integer.parseInt(CommandLineInputControl.SCANNER.next());
                if (res <= 0) throw new NegativeArraySizeException();
                return res;
            } catch (NumberFormatException e) {
                userInterface.promptErrorMessage("Invalid integer format. Please enter a valid integer.");
            } catch (NegativeArraySizeException e) {
                userInterface.promptErrorMessage("Invalid integer sign. Please enter a positive integer.");
            }
        }
    }

    public String inputString(String prompt) {
        this.userInterface.prompt(prompt);
        return CommandLineInputControl.SCANNER.next();
    }
}
