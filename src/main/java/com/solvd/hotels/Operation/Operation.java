package com.solvd.hotels.Operation;

import com.solvd.hotels.Hotels.MiraHotel;
import com.solvd.hotels.Hotels.Room1;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Operation {

    private final static Logger LOGGER = Logger.getLogger(Operation.class);


    public MiraHotel parse() {
        MiraHotel miraHotel = new MiraHotel();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("MiraHotel.json")) {

            String userCheckIn = checkInInput();

            LocalDate userCheckInParse = checkInPastCheck(userCheckIn);
            LOGGER.info("checkInPastCheck CHECKED");

            String userCheckOut =checkOutInput();

            LocalDate userCheckOutParse = checkInAfterCheckOutCheck(userCheckOut, userCheckInParse);
            LOGGER.info("checkInAfterCheckOutCheck CHECKED");
            JSONObject rootObj = (JSONObject) parser.parse(reader);
            String name = (String) rootObj.get("name");
            List<Room1> bookingsList = new LinkedList<>();
            JSONArray arrayBookingsRoom1 = (JSONArray) rootObj.get("room1");
            for (Object run : arrayBookingsRoom1) {
                JSONObject bookings = (JSONObject) run;
                String checkIn = (String) bookings.get("checkIn");
                String checkOut = (String) bookings.get("checkOut");
                LocalDate checkInJsonParse = null;
                try {
                    checkInJsonParse = LocalDate.parse(checkIn);
                } catch (DateTimeException e) {
                }
                LocalDate checkOutJsonParse = null;
                try {
                    checkOutJsonParse = LocalDate.parse(checkOut);
                } catch (DateTimeException e) {
                    e.printStackTrace();
                }
                bookedDatesCheck(userCheckInParse, userCheckOutParse, checkInJsonParse, checkOutJsonParse);
                LOGGER.info("bookedDatesCheck CHECKED");
                Room1 room1 = new Room1(checkIn, checkOut);
                bookingsList.add(room1);
            }
            Room1 newCheckIn = new Room1(userCheckIn, userCheckOut);
            bookingsList.add(newCheckIn);
            miraHotel.setName(name);
            miraHotel.setRoom1(bookingsList);
            System.out.println("Hotel " + miraHotel.getName());
            System.out.println("Date from " + userCheckIn + " till " + userCheckOut + " was booked successful !!!");

            return miraHotel;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkInInput(){
        System.out.println("Print checkIn date please");
        System.out.println("year-month-date");
        Scanner userCheckInSc = new Scanner(System.in);
        String userCheckIn = userCheckInSc.nextLine();
        return userCheckIn;
    }
    
    public String checkOutInput(){
        System.out.println("Print checkOut date please");
        System.out.println("year-month-date");
        Scanner userCheckOutSc = new Scanner(System.in);
        String userCheckOut = userCheckOutSc.nextLine();
        return userCheckOut;
    }

    public void bookedDatesCheck(LocalDate userCheckInParse, LocalDate userCheckOutParse, LocalDate checkInJsonParse, LocalDate checkOutJsonParse) {
        if (userCheckInParse.compareTo(checkInJsonParse) == 0 ||
                userCheckInParse.compareTo(checkOutJsonParse) == 0 ||
                userCheckInParse.compareTo(checkInJsonParse) > 0 &&
                        userCheckInParse.compareTo(checkOutJsonParse) < 0 ||
                userCheckOutParse.compareTo(checkInJsonParse) == 0 ||
                userCheckOutParse.compareTo(checkOutJsonParse) == 0 ||
                userCheckOutParse.compareTo(checkInJsonParse) > 0 &&
                        userCheckOutParse.compareTo(checkOutJsonParse) < 0 ||
                userCheckInParse.compareTo(checkInJsonParse) < 0 &&
                        userCheckOutParse.compareTo(checkOutJsonParse) > 0) {
            LOGGER.error("Date is already BOOKED ");
            LOGGER.error("Try another date please");
            System.exit(0);
        }
    }

    public LocalDate checkInAfterCheckOutCheck(String userCheckOut, LocalDate userCheckInParse) {
        LocalDate userCheckOutParse = null;
        try {
            userCheckOutParse = LocalDate.parse(userCheckOut);
            if (userCheckInParse.compareTo(userCheckOutParse) > 0) {
                LOGGER.info("CheckIn cant be after checkOut");
                System.exit(0);
            }
        } catch (DateTimeException e) {
            LOGGER.info("Please enter right checkOut");
            System.exit(0);
        }
        return userCheckOutParse;
    }


    public LocalDate checkInPastCheck(String userCheckIn) {
        LocalDate userCheckInParse = null;
        try {
            userCheckInParse = LocalDate.parse(userCheckIn);
            LocalDate localDate = LocalDate.now();
            if (userCheckInParse.compareTo(localDate) < 0) {
                LOGGER.info("checkIn cant be in the PAST");
                System.exit(0);
            }
        } catch (DateTimeException e) {
            LOGGER.info("Please enter right checkIn");
            System.exit(0);
        }
        return userCheckInParse;
    }

    public void writeDataToJson() {
        JSONObject main = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "MiraHotel");

        JSONObject booking = new JSONObject();
        booking.put("checkIn", "2021-03-16");
        booking.put("checkOut", "2021-03-17");

        JSONObject booking2 = new JSONObject();
        booking2.put("checkIn", "2021-03-18");
        booking2.put("checkOut", "2021-03-19");

        JSONObject booking3 = new JSONObject();
        booking3.put("checkIn", "2021-03-20");
        booking3.put("checkOut", "2021-03-21");

        JSONArray Room1 = new JSONArray();
        Room1.add(booking);
        Room1.add(booking2);
        Room1.add(booking3);
        main.put("name", "Adrian");
        main.put("Room1", Room1);

        JSONArray extraMain = new JSONArray();
        extraMain.add(main);
        try {
            FileWriter fileWriter = new FileWriter("new.json", true);
            fileWriter.write(extraMain.toJSONString());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
