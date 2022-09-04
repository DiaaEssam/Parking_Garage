package com.example.cli;

import com.example.domain.*;

public class CommandLineInterface {
    private final ParkingSpace PARKING_SPACE;
    private final CommandLineInputControl INPUT_CONTROL;
    private final CommandLineOutputControl OUTPUT_CONTROL;

    public CommandLineInterface(ParkingSpace parkingSpace) {
//        The garage (control object)
        this.PARKING_SPACE = parkingSpace;

//        The two main components that makes up the interface
//        1: The input control object, handles all the inputs
//        2: The output control object, handles all the outputs
        this.INPUT_CONTROL = new CommandLineInputControl(this);
        this.OUTPUT_CONTROL = new CommandLineOutputControl(this);

//        Get the number of parking slots from the garage keeper
        int numOfParkingSlots = INPUT_CONTROL.inputPositiveInt("Enter number of parking slots: ");

//        Get the parking slots dimensions
        OUTPUT_CONTROL.print("Enter the dimensions of each parking slot.");
        int[][] parkingSlotsDimensions = INPUT_CONTROL.inputParkingSlotsDimensions(numOfParkingSlots);

//        Initialize the parking slots with their dimensions
        this.PARKING_SPACE.initializeParkingSlots(parkingSlotsDimensions);

//        Prompt the garage keeper to choose a slot configuration
        this.changeSlotConfig();

//        Display the available parking slots
        this.OUTPUT_CONTROL.displayAvailableParkingSlots();
    }

    private void changeSlotConfig() {
        int slotConfigChoice;
        while (true) {
            slotConfigChoice = INPUT_CONTROL.inputPositiveInt("""
                    Choose a slot configuration:
                    [1] First come, first serve.
                    [2] Best fit.
                    >>\040""");
            if (slotConfigChoice != 1 && slotConfigChoice != 2)
                this.OUTPUT_CONTROL.displayErrorMessage("Invalid choice for slot configuration, please choose from the given options.");
            else break;
        }

        this.PARKING_SPACE.setSlotConfig(slotConfigChoice);
    }


    public void prompt(String prompt) {
        this.OUTPUT_CONTROL.prompt(prompt);
    }

    public void promptErrorMessage(String message) {
        this.OUTPUT_CONTROL.displayErrorMessage(message);
    }

    public ParkingSlot[] getAllParkingSlots() {
        return PARKING_SPACE.getParkingSlots();
    }


    public void loop() {
        while (true) {
            this.OUTPUT_CONTROL.displayMenu();
            int choice = this.INPUT_CONTROL.inputPositiveInt(">> ");

            switch (choice) {
                case 1 -> this.parkIn();
                case 2 -> this.parkOut();
                case 3 -> this.displayAvailableParkingSlots();
                case 4 -> this.displayTotalNumberOfVehicles();
                case 5 -> this.displayTotalIncome();
                case 6 -> this.changeSlotConfig();
                case 7 -> {
                    return;
                }
                default -> {
                    this.OUTPUT_CONTROL.displayErrorMessage("Invalid menu choice");
                }
            }
        }
    }

    private void displayTotalIncome() {
        this.OUTPUT_CONTROL.displayTotalIncome();
    }

    private void displayAvailableParkingSlots() {
        this.OUTPUT_CONTROL.displayAvailableParkingSlots();
    }

    private void displayTotalNumberOfVehicles() {
        this.OUTPUT_CONTROL.displayTotalNumberOfVehicles();
    }

    private void parkOut() {
        int slotId = this.INPUT_CONTROL.inputPositiveInt("Slot ID: ");

        long duration = this.PARKING_SPACE.parkOut(slotId).getSeconds();
        if (duration != 0) {
//            Print the duration in a formatted manner
            this.OUTPUT_CONTROL.print("Duration: " + String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60)));
//            Calculate the parking fees
            int fee = (int) ((duration / 60) / 60) * this.PARKING_SPACE.getHourlyRate();
            fee += (fee == 0 ? this.PARKING_SPACE.getHourlyRate() : 0);
            this.OUTPUT_CONTROL.print("Payment fee: LE " + fee);
//            Increase the total income with the parking fees
            this.PARKING_SPACE.increaseIncome(fee);
        } else {
//            if the returned duration is zero, this means there eas no vehicle parked in the slot with the specified id
            this.OUTPUT_CONTROL.displayErrorMessage("No vehicle parked in slot " + slotId);
        }
    }

    private void parkIn() {
//        Take vehicle data from the garage keeper

        String modelName = this.INPUT_CONTROL.inputString("Vehicle model name: ");

        int modelYear = this.INPUT_CONTROL.inputPositiveInt("Vehicle model year: ");

        int width = this.INPUT_CONTROL.inputPositiveInt("Vehicle width: ");

        int depth = this.INPUT_CONTROL.inputPositiveInt("Vehicle depth: ");

//        try to park in the vehicle, if the method returned true, it means the vehicle found a suitable parking slot
//        and is parked successfully
        if (!this.PARKING_SPACE.parkIn(modelName, modelYear, width, depth))
//            otherwise we notify the garage keeper there are no suitable slots for the vehicle
            this.OUTPUT_CONTROL.displayErrorMessage("No suitable slot found.");
    }

    public int getNumberOfVehicles() {
        return this.PARKING_SPACE.getNumberOfVehicles();
    }

    public int getIncome() {
        return this.PARKING_SPACE.getIncome();
    }
}
