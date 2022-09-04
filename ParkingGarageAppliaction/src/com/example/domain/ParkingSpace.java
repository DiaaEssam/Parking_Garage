package com.example.domain;

import com.example.slotconfiguration.BestFit;
import com.example.slotconfiguration.FirstComeFirstServe;
import com.example.slotconfiguration.SlotConfiguration;

import java.time.Duration;
import java.time.LocalDateTime;


public class ParkingSpace {
    //    A parking space has many parking slots
    private ParkingSlot[] parkingSlots;

    //    The active slot configuration used in picking the slot during park in
    private SlotConfiguration slotConfig;

    //    Constant for the hourly rate
    private final int HOURLY_RATE = 5;

    //    The number of vehicles parked in the parking space from the start
    private int numberOfVehicles = 0;

    //    Total income (increases with every park out with the parking fees)
    private int income = 0;

    //    This method takes an array of int pairs, each pair represent the dimensions of a parking slot,
//    the method initializes the parking slots with their corresponding dimensions
    public void initializeParkingSlots(int[][] parkingSlotsDimensions) {
//        Initialize the array of parking slots
        this.parkingSlots = new ParkingSlot[parkingSlotsDimensions.length];

        for (int i = 0; i < this.parkingSlots.length; i++)
            parkingSlots[i] = new ParkingSlot(
//                    parkingSlotsDimensions[i] is a pair of ints
//                    parkingSlotsDimensions[i][0] is the width
//                    parkingSlotsDimensions[i][1] is the depth
                    new Dimensions(
                            parkingSlotsDimensions[i][0],
                            parkingSlotsDimensions[i][1]
                    )
            );
    }

    //    The park in method takes the vehicle data, creates the vehicle, and then
//    assign it to a suitable parking slot (based on the active slot configuration)
    public boolean parkIn(String modelName, int modelYear, int width, int depth) {
//        Create the vehicle using the provided parameters
        Vehicle vehicle = new Vehicle(modelName, modelYear, width, depth);

//        Pick the most suitable slot for the created vehicle
//        the pickSlot() method is implemented in two different concrete classes
//        the used implementation is determined polymorphic-ally
        ParkingSlot pickedSlot = slotConfig.pickSlot(this.parkingSlots, vehicle);

//        if no slot is found, we return false
        if (pickedSlot != null) {
//            otherwise, we park in the vehicle in the picked slot
            pickedSlot.parkIn(vehicle);
//            then we increment the total number of vehicles parked
            this.incrementNumberOfVehicles();
            return true;
        } else return false;
    }

    private void incrementNumberOfVehicles() {
        this.numberOfVehicles++;
    }

    //    Park out a parked vehicle using its slot id
    public Duration parkOut(int slotId) {
//        Find the slot
        for (ParkingSlot slot :
                this.parkingSlots) {
            if (!slot.isAvailable() && slot.getID() == slotId) {
//                Get the vehicle's arrival time from the slot, then reset the slot's arrival time
                LocalDateTime arrivalTime = slot.getArrivalTime();
                slot.resetArrivalTime();
//                Empty the slot
                slot.parkOut();
//                Calculate the duration between the arrival time and the departure time
                return Duration.between(arrivalTime, LocalDateTime.now());
            }
        }
        return null;
    }

    //    Changes the slot configuration based on the slotConfig parameter (1: FirstComeFirstServe, 2: BestFit)
    public void setSlotConfig(int slotConfig) {
        if (slotConfig == 1) this.slotConfig = new FirstComeFirstServe();
        else if (slotConfig == 2) this.slotConfig = new BestFit();
    }

//    Getters
    public ParkingSlot[] getParkingSlots() {
        return parkingSlots;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public int getHourlyRate() {
        return this.HOURLY_RATE;
    }

    public void increaseIncome(int fee) {
        this.income += fee;
    }

    public int getIncome() {
        return this.income;
    }
}
