package com.FoodService.service;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Data
@Component
public class CronService{

    private String latestMessage;

    @Scheduled(cron = "*/3 * * * * *") // Runs every 3 seconds
    public void updateMessage() {
        latestMessage = "Hello, Shrikant! Current Time: " + System.currentTimeMillis();
    }
}