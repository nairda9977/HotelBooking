package com.solvd.hotels;

import com.solvd.hotels.Utils.JsonSimpleParser;
import org.apache.log4j.Logger;



public class Executor {
    private  final static Logger LOGGER = Logger.getLogger(Executor.class);
    public static void main(String[] args) {
        JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
        jsonSimpleParser.parse();



    }

}
