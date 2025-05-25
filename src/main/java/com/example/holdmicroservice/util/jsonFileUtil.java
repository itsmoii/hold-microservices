package com.example.holdmicroservice.util;

import com.example.holdmicroservice.model.holdStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class jsonFileUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<holdStatus> readHoldFile(String path){
        try{
            File file = new File(path);
            if(!file.exists()) return new ArrayList<>();
            return Arrays.asList(mapper.readValue(file, holdStatus[].class));

        }catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();

        }
    }

    public static void writeHoldFile(String path, List<holdStatus> data){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);

        }catch(IOException e){
            e.printStackTrace();


        }
    }



}
