package com.FoodService.controller;

import com.FoodService.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronController {

    @Autowired
    private CronService cronService;

    @GetMapping("/cronjob")
    public String getMessage() {
        return cronService.getLatestMessage();
    }
}