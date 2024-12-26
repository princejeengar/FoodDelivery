package com.FoodService.repository;

import com.FoodService.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepo extends JpaRepository<Dishes, Long> {
}