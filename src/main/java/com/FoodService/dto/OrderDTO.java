package com.FoodService.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long orderID;
    private String orderNumber;
    private AppUserDTO appUser;
    private DishesDTO dishes;
}