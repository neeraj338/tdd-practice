package com.tdd.practice.parkinglot.farerule;

public class TwoHourRule implements IFareRule {

    @Override
    public boolean test(int durationHour) {
        return durationHour>=2;
    }

    @Override
    public double reduce(int durationHour, double pricePerHour) {
        if(test(durationHour))
            return pricePerHour;

        return 0.0d;
    }
}
