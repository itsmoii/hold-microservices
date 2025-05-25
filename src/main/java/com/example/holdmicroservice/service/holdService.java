package com.example.holdmicroservice.service;


import com.example.holdmicroservice.model.holdStatus;
import com.example.holdmicroservice.util.jsonFileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class holdService {

    private static final String HOLD_FILE = "holds.json";

    public holdStatus getHoldStatus(String studentId){
        List<holdStatus> holds = jsonFileUtil.readHoldFile(HOLD_FILE);

        return holds.stream()
                .filter(h -> h.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public void updateHoldStatus(List<holdStatus> allHolds) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(HOLD_FILE);

        // Write the entire list as one JSON array
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, allHolds);
    }

    public List<holdStatus> getAllHolds() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(HOLD_FILE);

        if(!file.exists()) {
            return new ArrayList<>();
        }

        return Arrays.asList(mapper.readValue(file, holdStatus[].class));
    }

}
