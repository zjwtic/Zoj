package com.example.zojcodesandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health")
    public String healthcheck(){
        return "ok";
    }

}
