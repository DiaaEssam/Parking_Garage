package com.example.slotconfiguration;

import com.example.domain.ParkingSlot;
import com.example.domain.Vehicle;

public interface SlotConfiguration {
    ParkingSlot pickSlot(ParkingSlot[] parkingSlots, Vehicle vehicleToPark);
}
