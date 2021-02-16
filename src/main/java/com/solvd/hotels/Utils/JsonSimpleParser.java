package com.solvd.hotels.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.solvd.hotels.Hotels.Room1;
import com.solvd.hotels.Hotels.Root;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import java.io.*;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class JsonSimpleParser {
    private  final static Logger LOGGER = Logger.getLogger(JsonSimpleParser.class);

    public void writeObjToJson(String path, Object obj){

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(Paths.get(path).toFile(), obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public Root parse(){
        Root root = new Root();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("new.json")){
            System.out.println("Print checkIn date please");
            System.out.println("year-month-date");
            Scanner userCheckInSc = new Scanner(System.in);
            String userCheckIn = userCheckInSc.nextLine();
            LocalDate userCheckInParse = null;
            try {
                userCheckInParse = LocalDate.parse(userCheckIn);
            }catch (DateTimeException e){
                e.printStackTrace();
            }
            System.out.println("Print checkOut date please");
            System.out.println("year-month-date");
            Scanner userCheckOutSc = new Scanner(System.in);
            String userCheckOut = userCheckOutSc.nextLine();
            LocalDate userCheckOutParse;
            try {
                userCheckOutParse = LocalDate.parse(userCheckOut);
            }catch (DateTimeException e){
                e.printStackTrace();
            }

            JSONObject rootObj = (JSONObject) parser.parse(reader);
            String name = (String) rootObj.get("name");
            List<Room1>bookingsList = new LinkedList<>();

            JSONArray arrayBookingsRoom1 = (JSONArray) rootObj.get("room1");
            for (Object run: arrayBookingsRoom1){
                JSONObject bookings = (JSONObject) run;
                String checkIn = (String) bookings.get("checkIn");
                String checkOut = (String) bookings.get("checkOut");

                LocalDate checkInJsonParse = null;
                try {
                    checkInJsonParse = LocalDate.parse(checkIn);
                }catch (DateTimeException e){
                    e.printStackTrace();
                }
                LocalDate checkOutJsonParse=null;
                try{
                    checkOutJsonParse = LocalDate.parse(checkOut);
                }catch (DateTimeException e){
                    e.printStackTrace();
                }

                if (userCheckInParse.compareTo(checkInJsonParse)==0||
                        userCheckInParse.compareTo(checkInJsonParse)>0&&
                                userCheckInParse.compareTo(checkOutJsonParse)<0||
                                userCheckInParse.compareTo(checkOutJsonParse)==0){
                    System.out.println("Date is already booked ");
                    System.out.println("Try another date please");
                    System.exit(0);
                }


                Room1 room1 = new Room1(checkIn, checkOut);

                bookingsList.add(room1);

                }
            Room1 newCheckIn = new Room1(userCheckIn, userCheckOut);
            bookingsList.add(newCheckIn);
            root.setName(name);
            root.setRoom1(bookingsList);
            System.out.println("Hotel "+root.getName());
            System.out.println("Date from " +userCheckIn+" till "+userCheckOut+" was booked successful !!!");

            return root;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void isAvailableCheck(String checkInStr, String checkOutStr){
        LocalDate checkIn=null;
        try {
            LocalDate localDate = LocalDate.now();
            checkIn = LocalDate.parse(checkInStr);
            if(checkIn.compareTo(localDate)<=0){
                System.err.println("Booking date cant be in past");
                System.exit(0);
            }
        }catch (DateTimeException e ){
            System.out.println("Enter right data, start again");
            System.exit(0);
        }
        LocalDate checkOut=null;
        try {
            LocalDate localDate = LocalDate.now();
            checkOut = LocalDate.parse(checkOutStr);
            if(checkIn.compareTo(localDate)<=0){
                System.err.println("Booking date cant be in past");
                System.exit(0);
            }
        }catch (DateTimeException e ){
            System.out.println("Enter right data, start again");
            System.exit(0);
        }




    }

public void writeDataToJson(){
    JSONObject main = new JSONObject();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "MiraHotel");

    JSONObject  booking = new JSONObject();
    booking.put("checkIn", "2021-03-16");
    booking.put("checkOut", "2021-03-17");

    JSONObject booking2 = new JSONObject();
    booking2.put("checkIn","2021-03-18");
    booking2.put("checkOut", "2021-03-19");

    JSONObject booking3 = new JSONObject();
    booking3.put("checkIn","2021-03-20");
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
    }catch (Exception e){
        e.printStackTrace();
    }
}

    public void addingInExistJsonFile(){


    }




}
