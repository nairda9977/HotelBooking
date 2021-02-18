package com.solvd.hotels.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonExec {

    private static final Logger LOGGER = Logger.getLogger(JsonExec.class);

    public String convertStrToJson(Object obj){
        String jackStr = null;

            try {
                jackStr = new ObjectMapper().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        return jackStr;
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
