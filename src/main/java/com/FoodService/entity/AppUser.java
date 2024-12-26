package com.FoodService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AppUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AppUserID;

    @Column(unique = true, nullable = false)
    private String Username;

    @Column(unique = true, nullable = false)
    private String email;
}