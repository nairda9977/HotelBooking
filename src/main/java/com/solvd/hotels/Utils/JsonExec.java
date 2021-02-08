package com.solvd.hotels.Utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.hotels.Hotels.MirabelAntaliaResort;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonExec {

    private static final Logger LOGGER = Logger.getLogger(JsonExec.class);


    public MirabelAntaliaResort getDataFromJson(String path){
        File file = new File(path);
        MirabelAntaliaResort mira = null;

            ObjectMapper obj = new ObjectMapper();
            try {
               mira= obj.readValue(file, MirabelAntaliaResort.class);
                System.out.println(mira.getCheckOut());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mira;
}

public void convertJavaToJsonFile(Object obj, String path){

    ObjectMapper objectMapper = new ObjectMapper();
    try {
        objectMapper.writeValue(Paths.get(path).toFile(),obj);
        LOGGER.info("Write to file finished");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
