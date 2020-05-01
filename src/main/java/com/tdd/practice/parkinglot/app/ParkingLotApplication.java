package com.tdd.practice.parkinglot.app;


import com.tdd.practice.parkinglot.farerule.TwoHourRule;
import com.tdd.practice.parkinglot.model.Slot;
import com.tdd.practice.parkinglot.model.Vehicle;
import com.tdd.practice.parkinglot.service.Calculator;
import com.tdd.practice.parkinglot.service.Parkinglot;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParkingLotApplication {

    private static final Logger logger = Logger.getLogger(ParkingLotApplication.class.getName());

    private static Calculator calculator;
    private static Parkinglot parkinglot;

    private static void init(){
        calculator = new Calculator(10);
        calculator.addFareRule(new TwoHourRule());
    }

    public static void main(String[] arg){
        if(arg.length != 1 ){
            logger.log(Level.INFO, "Please supply the full input file path");
            System.exit(0);
        }
        String filePath = arg[0];
        try(BufferedReader bf = new BufferedReader(new FileReader(new File(filePath)));
        ){
            String line = null;
            boolean initSlotOnce = true;
            while(StringUtils.isNotBlank( (line = bf.readLine()))){

                if(initSlotOnce && checkPattern(line, "create_parking_lot")){
                    String slotLength = parseForInput(line, "create_parking_lot").get(0);
                    if(NumberUtils.isDigits(slotLength))
                    {
                        init();
                        parkinglot = new Parkinglot(Integer.parseInt(slotLength), calculator);
                        println(String.format("Created parking lot with %s slots", slotLength));
                    }
                    initSlotOnce = false;
                }else if(null != parkinglot && checkPattern(line, "park")){
                    String registrationNumber = parseForInput(line, "park").get(0);
                    Vehicle vehicle = new Vehicle(registrationNumber);
                    Slot slot = parkinglot.park(vehicle);
                    if(null == slot){
                        println(String.format("Sorry, parking lot is full"));
                    }else{
                        println(String.format("Allocated slot number: %s", slot.getPosition()+1));
                    }
                }else if(null != parkinglot && checkPattern(line, "leave")){
                    List<String> registrationNumberAndHour = parseForInput(line, "leave");
                    String registrationNumber = registrationNumberAndHour.get(0);
                    String durationHour = registrationNumberAndHour.get(1);

                    Slot slot= parkinglot.unpark(new Vehicle(registrationNumber)
                            , NumberUtils.isDigits(durationHour)
                                    ? Integer.parseInt(durationHour) : 0);
                    if(slot == null){
                        println(String.format("Registration number %s not found", registrationNumber));
                    } else {
                        println(String.format("Registration number %s " +
                                "with Slot Number %s is free with Charge %s"
                        , registrationNumber, slot.getPosition()+1, slot.getPrice()));
                    }
                }else if(null != parkinglot && checkPattern(line, "status")){
                    println("Slot No. \t Registration No.");
                    List<Slot> slots = parkinglot.status();
                    slots.stream()
                            .forEach(x -> println(x.toString()) );
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format("file processing error :: %s", filePath), e);
        }
    }

    private static List<String> parseForInput(String line, String pattern) {
        String regex = pattern+"|\\s+";

        return Arrays.stream(line.split(regex))
                .filter(StringUtils::isNotBlank)
                .map(x->x.trim())
                .collect(Collectors.toList());
    }

    private static boolean checkPattern(String line, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        return p.matcher(line).find();
    }

    private static void println(String string){
        System.out.println(string);
    }
}
