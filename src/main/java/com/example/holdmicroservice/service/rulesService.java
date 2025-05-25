package com.example.holdmicroservice.service;

import com.example.holdmicroservice.model.holdRules;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class rulesService {

    public static final  String RULE_FILE = "rules.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public holdRules getRules(){
        try{
            File file = new File(RULE_FILE);

            if(!file.exists()){
                holdRules defaultRules = new holdRules(false, false, false, false, false);
                updateRules(defaultRules);
                return defaultRules;
            }

            return mapper.readValue(file, holdRules.class);

        }catch(IOException e){
            e.printStackTrace();
            return null;

        }

    }

    public holdRules updateRules(holdRules newRules){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RULE_FILE), newRules);
            return newRules;
        }catch(IOException e){
            e.printStackTrace();
            return null;

        }

    }


}
