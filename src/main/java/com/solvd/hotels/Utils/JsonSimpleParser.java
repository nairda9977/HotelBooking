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
import java.util.LinkedList;
import java.util.List;


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

        Room1 room11 = new Room1("2021-05-06", "2021-05-10");  /*етот обєкт хочу добавить*/

        try (FileReader reader = new FileReader("new.json");){
            JSONObject rootObj = (JSONObject) parser.parse(reader);
            String name = (String) rootObj.get("name");

            List<Room1>room1s = new LinkedList<>();

            JSONArray arrayPeople = (JSONArray) rootObj.get("room1");
            for (Object run: arrayPeople){
                JSONObject bookings = (JSONObject) run;
                String checkIn = (String) bookings.get("checkIn");
                String checkOut = (String) bookings.get("checkOut");

                Room1 room1 = new Room1(checkIn, checkOut);

                room1s.add(room1);

                JSONObject check = new JSONObject();
                JSONObject main = new JSONObject();
                check.put("checkIn", checkIn);
                check.put("checkOut", checkOut);

                JSONArray jsonArray = new JSONArray();
                jsonArray.add(check);
                main.put("room1", jsonArray);
                main.put("name", "Adrian");

                FileWriter fileWriter = new FileWriter("new.json");
                fileWriter.write(main.toJSONString());
                fileWriter.flush();

            }
            room1s.add(room11);
            root.setName(name);
            root.setRoom1(room1s);



            return root;
        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
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
        fileWriter.write(main.toJSONString());
        fileWriter.flush();
    }catch (Exception e){
        e.printStackTrace();
    }
}

    public void addingInExistJsonFile(){


    }




}
