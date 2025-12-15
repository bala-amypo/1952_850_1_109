package com.example.demo.controller;

import org.springframework.web.bind.annotations.GetMapping;
import org.springframework.web.bind.annotations.RestController;

@RestController
public class Controller {

    @GetMapping("/simple-status")
    String print()
    {
        return "Credit Card Reward Maximizer is running";
    }

}

