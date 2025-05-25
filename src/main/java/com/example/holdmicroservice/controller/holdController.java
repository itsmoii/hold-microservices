package com.example.holdmicroservice.controller;

import com.example.holdmicroservice.model.holdStatus;
import com.example.holdmicroservice.service.holdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/holds")
public class holdController {

    @Autowired
    private holdService service;

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getHold(@PathVariable String studentId){
        holdStatus status = service.getHoldStatus(studentId);
        if (status == null){
            holdStatus defaultStatus = new holdStatus();
            defaultStatus.setStudentId(studentId);
            defaultStatus.setHold(false);
            return ResponseEntity.ok(defaultStatus);
        }

        return ResponseEntity.ok(status);
    }

    @PostMapping("/")
    public ResponseEntity<String> updateHold(@RequestBody List<holdStatus> newHolds) throws IOException {
        List<holdStatus> existingHolds = new ArrayList<>(service.getAllHolds());

        for (holdStatus newHold : newHolds) {
            boolean updated = false;

            for (holdStatus hold : existingHolds) {
                if (hold.getStudentId().equals(newHold.getStudentId())) {
                    hold.setHold(newHold.isHold());
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                existingHolds.add(newHold);
            }
        }

        service.updateHoldStatus(existingHolds);
        return ResponseEntity.ok("Hold data updated for all students");
    }




}
