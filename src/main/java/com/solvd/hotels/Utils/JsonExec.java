package com.solvd.hotels.Utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonExec {

    private static final Logger LOGGER = Logger.getLogger(JsonExec.class);



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
