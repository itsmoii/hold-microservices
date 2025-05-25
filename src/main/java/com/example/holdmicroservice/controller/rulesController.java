package com.example.holdmicroservice.controller;

import com.example.holdmicroservice.model.holdRules;
import com.example.holdmicroservice.service.rulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class rulesController {

    @Autowired
    private rulesService service;

    @GetMapping
    public holdRules getRules(){
        return service.getRules();
    }

    @PostMapping
    public holdRules updateRules(@RequestBody holdRules newRules){
        return service.updateRules(newRules);
    }

}
