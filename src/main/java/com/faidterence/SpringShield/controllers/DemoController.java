package com.faidterence.SpringShield.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/api/v1/demo")
    public String demo(){
        return "Welcome to Spring Shield!";
    }

    @GetMapping("api/v1/admin/demo")
    public String adminDemo(){
        return "Welcome to Spring Shield Admin!";
    }
}
