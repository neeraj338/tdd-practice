package com.tdd.practice.parkinglot.model;


import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
//@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Slot {

    @EqualsAndHashCode.Include
    private Vehicle vehicle;

    @Builder.Default private int position = -1;

    @Builder.Default private int durationHour = 0;

    @Builder.Default private double price = 0.0d;

    public Slot(Vehicle v, int position){
        this.vehicle =v;
        this.position = position;
    }

    public Slot(Vehicle v){
        this.vehicle =v;
    }

    @Override
    public String toString(){
        return String.format(" %s \t %s",  position + 1, vehicle.getRegistrationNumber());
    }

}
