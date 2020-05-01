package com.tdd.practice.parkinglot;

import com.tdd.practice.parkinglot.farerule.TwoHourRule;
import com.tdd.practice.parkinglot.model.Slot;
import com.tdd.practice.parkinglot.model.Vehicle;
import com.tdd.practice.parkinglot.service.Calculator;
import com.tdd.practice.parkinglot.service.Parkinglot;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class ParkinglotTest {

    private static Calculator calculator;

    @BeforeClass
    public static void setup(){
        calculator = new Calculator(10);
        calculator.addFareRule(new TwoHourRule());
    }

    @Test
    public void testCreateParkinglotOfGivenSize(){
        Parkinglot parkinglot = new Parkinglot(10, calculator);

        Assert.assertThat("validate max lot size",10
                , is(Matchers.equalTo(parkinglot.getParkingCapacity())) );
    }

    @Test
    public void testParkVehicle(){
        Parkinglot parkinglot = new Parkinglot(10, calculator);
        Slot slot = parkinglot.park(new Vehicle("KA-01-HH-1234"));
        Assert.assertThat(slot.getPosition(), Matchers.allOf(Matchers.lessThan(10), Matchers.greaterThanOrEqualTo(0)) );
    }

    @Test
    public void testParkingfull(){
        Parkinglot parkinglot = new Parkinglot(2, calculator);
        parkinglot.park(new Vehicle("KA-01-HH-1234"));
        parkinglot.park(new Vehicle("KA-01-HH-5467"));
        Slot slot = parkinglot.park(new Vehicle("KA-01-HH-0011"));
        Assert.assertThat(slot, Matchers.is(Matchers.nullValue()) );
    }



    @Test
    public void testUnparkVehicle(){
        Parkinglot parkinglot = new Parkinglot(3, calculator);
        parkinglot.park(new Vehicle("KA-01-HH-1234"));
        parkinglot.park(new Vehicle("KA-01-HH-5467"));
        Vehicle vehicle001 = new Vehicle("KA-01-HH-0011");
        Slot slot001 = parkinglot.park(vehicle001);

        Slot freedSlot = parkinglot.unpark(vehicle001, 4);

        Assert.assertThat(freedSlot, Matchers.is(Matchers.equalTo(slot001)));
    }

    @Test
    public void testVehicleNotFount(){
        Parkinglot parkinglot = new Parkinglot(2, calculator);
        parkinglot.park(new Vehicle("KA-01-HH-1234"));
        Slot slot = parkinglot.unpark(new Vehicle("KA-01-HH-0011"), 10);
        Assert.assertThat(slot, Matchers.is(Matchers.nullValue()) );
    }

    @Test
    public void testVehickeParckedStatus(){

        Parkinglot parkinglot = new Parkinglot(3, calculator);
        Slot slot1234 = parkinglot.park(new Vehicle("KA-01-HH-1234"));
        Slot slot5467 = parkinglot.park(new Vehicle("KA-01-HH-5467"));

        //park and unpark vehicle001
        Vehicle vehicle001 = new Vehicle("KA-01-HH-0011");
        parkinglot.park(vehicle001);
        parkinglot.unpark(vehicle001, 4);

        List<Slot> slots = parkinglot.status();
        Assert.assertThat(slots, Matchers.contains(slot1234, slot5467));
    }

    @Test
    public void testVehickeUnparkedParkingPriceCalulationForFourHour(){

        Parkinglot parkinglot = new Parkinglot(3, calculator);
        parkinglot.park(new Vehicle("KA-01-HH-1234"));
        parkinglot.park(new Vehicle("KA-01-HH-5467"));

        //park and unpark vehicle001
        Vehicle vehicle001 = new Vehicle("KA-01-HH-0011");
        parkinglot.park(vehicle001);
        Slot unpark001 =  parkinglot.unpark(vehicle001, 4);

        Assert.assertThat(unpark001.getPrice(), Matchers.closeTo(30, 0.01));
    }

    @Test
    public void testVehickeUnparkedParkingPriceCalulationForOneHour(){

        Parkinglot parkinglot = new Parkinglot(3, calculator);
        parkinglot.park(new Vehicle("KA-01-HH-1234"));
        parkinglot.park(new Vehicle("KA-01-HH-5467"));

        //park and unpark vehicle001
        Vehicle vehicle001 = new Vehicle("KA-01-HH-0011");
        parkinglot.park(vehicle001);
        Slot unpark001 =  parkinglot.unpark(vehicle001, 1);

        Assert.assertThat(unpark001.getPrice(), Matchers.closeTo(10, 0.01));
    }
}
