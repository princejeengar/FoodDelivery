package com.FoodService.service;

import com.FoodService.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderCleanupScheduler {

    @Autowired
    private OrderRepo orderRepo;

    //Run at midnight every day
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteAllOrders() {
        System.out.println("Deleting all orders at midnight...");
        orderRepo.deleteAll();
        System.out.println("All orders deleted successfully.");
    }
}