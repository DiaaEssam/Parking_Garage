package com.example.slotconfiguration;

import com.example.domain.ParkingSlot;
import com.example.domain.Vehicle;

public class BestFit implements SlotConfiguration {
//    First, we find the slot with the greatest dimensions using simple linear search
    @Override
    public ParkingSlot pickSlot(ParkingSlot[] parkingSlots, Vehicle vehicleToPark) {
        ParkingSlot maximumSlot = parkingSlots[0];
        for (ParkingSlot slot :
                parkingSlots) {
            if (!slot.smallerThan(maximumSlot)) {
                maximumSlot = slot;
            }
        }
//        Then, we compare this slot with the greatest dimensions with every other slot in the garage
//        to find the slot with the minimum dimensions that can fit the vehicle to park
        ParkingSlot optimalSlot = maximumSlot;
        for (ParkingSlot slot :
                parkingSlots) {
            if (slot.isAvailable() && slot.smallerThan(optimalSlot) && vehicleToPark.fitsIn(slot)) {
                optimalSlot = slot;
            }
        }
//        This makes sure the slot is returned only if it is available and the vehicle to park fits in it
        if (optimalSlot.isAvailable() && vehicleToPark.fitsIn(optimalSlot))
            return optimalSlot;
        else return null;
    }
}
