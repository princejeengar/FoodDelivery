package com.FoodService.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DishID;

    @Column(nullable = false)
    private String DishName;

    @ManyToOne
    @JoinColumn(name = "RestaurantID", nullable = false)
    private Restaurant restaurant;
}