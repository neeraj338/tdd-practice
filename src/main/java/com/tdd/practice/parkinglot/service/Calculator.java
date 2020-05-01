package com.tdd.practice.parkinglot.service;


import com.tdd.practice.parkinglot.farerule.IFareRule;

import java.util.ArrayList;
import java.util.List;


public class Calculator {

    private double pricePerHour;

    private List<IFareRule> fareRules;

    public Calculator(double pricePerHour){
        this.pricePerHour = pricePerHour;
        fareRules = new ArrayList<>();
    }

    public void addFareRule(IFareRule rule){
        this.fareRules.add(rule);
    }

    public double calculatePrice(int durationHour) {
        double totalAmount = durationHour * pricePerHour;
        for (IFareRule rules: fareRules) {
            totalAmount = totalAmount - rules.reduce(durationHour, pricePerHour);
        }
        return totalAmount;
    }
}
