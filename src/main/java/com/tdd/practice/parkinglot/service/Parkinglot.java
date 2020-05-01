package com.tdd.practice.parkinglot.service;

import com.tdd.practice.parkinglot.model.Slot;
import com.tdd.practice.parkinglot.model.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Parkinglot {

    private static final Logger logger = Logger.getLogger(Parkinglot.class.getName());

    private List<Slot> availableSlots;

    private Deque<Integer> deque;

    private Calculator priceCalculator;

    private int parkingCapacity = 0;

    public Parkinglot(int parkingCapacity, Calculator calc) {
        availableSlots = new ArrayList<>(parkingCapacity);
        priceCalculator = calc;
        this.parkingCapacity = parkingCapacity;
        // initialize empty slot to track free and used space
        deque = new ArrayDeque<>(parkingCapacity);
        for (int i = 0; i < parkingCapacity; i++) {
            deque.addLast(Integer.valueOf(i));
        }
    }

    public int getParkingCapacity() {
        return this.parkingCapacity;
    }

    @Nullable
    public Slot park(Vehicle vehicle) {
        if(this.deque.isEmpty()){
            return null;
        }
        int position = this.deque.pop();
        Slot slot = new Slot(vehicle, position);
        availableSlots.add(position, slot);

        return slot;
    }

    @Nullable
    public Slot unpark(Vehicle vehicle, int durationHour) {
     int parkSlotIndex = this.availableSlots.indexOf(new Slot(vehicle));
     if(-1 == parkSlotIndex){
         return null;
     }
     Slot slot = this.availableSlots.get(parkSlotIndex);
     slot.setDurationHour(durationHour);
     slot.setPrice(this.priceCalculator.calculatePrice(durationHour));

     this.availableSlots.set(parkSlotIndex, null);
     this.deque.addLast(parkSlotIndex);

     return slot;
    }

    @NotNull
    public List<Slot> status() {
        return availableSlots
                .stream().filter(x-> null != x)
                .sorted(Comparator.comparing(Slot::getPosition))
                .collect(Collectors.toList());
    }
}
