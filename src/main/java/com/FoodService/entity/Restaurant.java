package com.FoodService.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RestaurantId;

    @Column(nullable = false)
    private String RestaurantName;

    @Column(nullable = false)
    private String RestaurantAddress;
}