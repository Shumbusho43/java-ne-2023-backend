package com.ne.java.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Welcome {
    @GetMapping
    public String Welcome(){
        return "WELCOME TO THE AppTitle backend";
    }
}
