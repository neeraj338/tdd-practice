package com.tdd.practice.parkinglot.farerule;

public interface IFareRule {

    boolean test(int durationHour);

    double reduce(int durationHour, double pricePerHour);
}
