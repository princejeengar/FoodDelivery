package com.FoodService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;

@Data
@Entity
@NoArgsConstructor
@Table(name = "OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "AppUserID", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "DishID", nullable = false)
    private Dishes dishes;

    @PrePersist
    private void generateOrderNumber() {
        this.orderNumber = "ORD-" + String.format("%08d", new Random().nextInt(100000000));
    }
}