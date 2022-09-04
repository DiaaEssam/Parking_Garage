package com.example.slotconfiguration;

import com.example.domain.ParkingSlot;
import com.example.domain.Vehicle;

public class FirstComeFirstServe implements SlotConfiguration {

    @Override
    public ParkingSlot pickSlot(ParkingSlot[] parkingSlots, Vehicle vehicleToPark) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isAvailable() && vehicleToPark.fitsIn(slot)) {
                return slot;
            }
        }
        return null;
    }
}
