package com.example.domain;

public class Vehicle {
    public static int latestId = 1;
    private final int ID;
    private final VehicleModel MODEL;
    private final Dimensions DIMENSIONS;

    public Vehicle(String modelName, int modelYear, int width, int depth) {
        this.ID = Vehicle.latestId++;
        this.MODEL = new VehicleModel(modelName, modelYear);
        this.DIMENSIONS = new Dimensions(width, depth);
    }

    public int getID() {
        return ID;
    }

    public VehicleModel getModel() {
        return MODEL;
    }

    public Dimensions getDimensions() {
        return DIMENSIONS;
    }

    @Override
    public String toString() {
//        [ID] MODEL (WIDTH x DEPTH)
        return "[" + this.ID + "] " + this.MODEL + " " + this.DIMENSIONS;
    }

//    Returns true if the slot provided has dimensions bigger than the vehicle's dimensions
    public boolean fitsIn(ParkingSlot slot) {
        return (this.DIMENSIONS.getWidth() <= slot.getDimensions().getWidth()) &&
                (this.DIMENSIONS.getDepth() <= slot.getDimensions().getDepth());
    }
}
