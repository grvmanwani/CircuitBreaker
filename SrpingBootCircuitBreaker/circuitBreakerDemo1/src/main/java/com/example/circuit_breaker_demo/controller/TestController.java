package com.example.circuit_breaker_demo.controller;

import com.example.circuit_breaker_demo.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private Test test;

    @GetMapping("/data")
    private ResponseEntity<Integer> getData(){

        return ResponseEntity.ok(test.getData());
    }
}
