package com.FoodService.dto;

import lombok.Data;

@Data
public class DishesDTO {
    private Long dishID;
    private String dishName;
    private RestaurantDTO restaurant;
}