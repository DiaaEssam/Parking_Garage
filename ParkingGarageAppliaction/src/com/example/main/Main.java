package com.example.main;

import com.example.cli.CommandLineInterface;
import com.example.domain.ParkingSpace;

public class Main {
//    The entry point of the Parking Garage Application
    public static void main(String[] args) {
//        First, we create the garage object
        ParkingSpace garage = new ParkingSpace();
//        Then we create the user interface object and pass it the garage object
        CommandLineInterface ui = new CommandLineInterface(garage);
//        Start the menu loop
        ui.loop();
    }
}
