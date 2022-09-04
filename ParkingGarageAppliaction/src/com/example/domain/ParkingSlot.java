package com.example.domain;

import java.time.LocalDateTime;

public class ParkingSlot {
    public static int latestId = 1;

    private final int ID;
    private final Dimensions DIMENSIONS;
    private LocalDateTime arrivalTime;
    private Vehicle parkedVehicle;

    public ParkingSlot(Dimensions dimensions) {
        this.ID = ParkingSlot.latestId++;
        this.DIMENSIONS = dimensions;
        this.parkedVehicle = null;
        this.arrivalTime = null;
    }

    public Dimensions getDimensions() {
        return DIMENSIONS;
    }

    public boolean isAvailable() {
        return this.parkedVehicle == null;
    }

    public int getID() {
        return ID;
    }

    public void parkIn(Vehicle vehicle) {
        this.arrivalTime = LocalDateTime.now();
        this.parkedVehicle = vehicle;
    }

    public void parkOut() {
//        Vehicle v = this.parkedVehicle;
        this.parkedVehicle = null;
    }

    public boolean smallerThan(ParkingSlot otherSlot) {
        return (this.DIMENSIONS.getWidth() <= otherSlot.getDimensions().getWidth()) &&
                (this.DIMENSIONS.getDepth() <= otherSlot.getDimensions().getDepth());
    }

    @Override
    public String toString() {

        return "Slot " + this.ID + " " + this.DIMENSIONS + ": " + (this.parkedVehicle != null ? this.parkedVehicle : "empty");
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void resetArrivalTime() {
        this.arrivalTime = null;
    }


}
