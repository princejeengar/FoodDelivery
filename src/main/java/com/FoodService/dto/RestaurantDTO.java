package com.FoodService.dto;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long restaurantID;
    private String restaurantName;
    private String restaurantAddress;
}