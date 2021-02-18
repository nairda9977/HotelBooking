package com.solvd.hotels;

import com.solvd.hotels.Hotels.MiraHotel;
import com.solvd.hotels.Operation.Operation;
import com.solvd.hotels.Utils.JsonExec;
import org.apache.log4j.Logger;


public class Executor {
    private  final static Logger LOGGER = Logger.getLogger(Executor.class);
    public static void main(String[] args) {
        Operation jsonSimpleParser = new Operation();
        MiraHotel root = jsonSimpleParser.parse();

        JsonExec jsonExec = new JsonExec();
        jsonExec.convertJavaToJsonFile(root, "MiraHotel.json");

    }

}
