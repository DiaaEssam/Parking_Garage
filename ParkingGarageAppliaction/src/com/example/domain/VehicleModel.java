package com.example.domain;

public class VehicleModel {
    private final String NAME;
    private final int YEAR;

    public VehicleModel(String modelName, int modelYear) {
        this.NAME = modelName;
        this.YEAR = modelYear;
    }

    public String modelName() {
        return NAME;
    }

    public int modelYear() {
        return YEAR;
    }

    @Override
    public String toString() {
        return this.NAME + ", " + this.YEAR;
    }
}
