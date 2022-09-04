package com.example.cli;

import com.example.domain.ParkingSlot;

public class CommandLineOutputControl {
    private CommandLineInterface userInterface;

    public CommandLineOutputControl(CommandLineInterface cli) {
        this.userInterface = cli;
    }

    public void prompt(String message) {
        System.out.print(message);
    }

    public void print(Object message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String errorMessage) {
        this.print("\n" + errorMessage + "\n");
    }

    public void displayAvailableParkingSlots() {
        this.print("\nAvailable Parking slots:");
        for (ParkingSlot slot : userInterface.getAllParkingSlots()) {
            if (slot.isAvailable()) this.print(slot);
        }
        this.print('\n');
    }

    public void displayTotalNumberOfVehicles() {
        this.print("\nTotal number of vehicles: " + userInterface.getNumberOfVehicles() + '\n');
    }

    public void displayMenu() {
        this.print("\nChoose an action from the menu below:");
        this.print("[1] Park in a vehicle.");
        this.print("[2] Park out a vehicle.");
        this.print("[3] Display available parking slots.");
        this.print("[4] Display total number of vehicles.");
        this.print("[5] Display total income.");
        this.print("[6] Change active slot configuration.");
        this.print("[7] Exit.\n");
    }

    public void displayTotalIncome() {
        this.print("\nTotal income: " + this.userInterface.getIncome() + "\n");
    }
}
