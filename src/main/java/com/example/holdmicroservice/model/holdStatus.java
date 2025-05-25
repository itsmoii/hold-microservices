package com.example.holdmicroservice.model;

import java.util.List;

public class holdStatus {

    private String studentId;
    private boolean hold;

    public holdStatus(){}

    public holdStatus(String studentId, boolean hold) {
        this.studentId = studentId;
        this.hold = hold;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public boolean isHold() {
        return hold;
    }

    // Setters

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }
}
