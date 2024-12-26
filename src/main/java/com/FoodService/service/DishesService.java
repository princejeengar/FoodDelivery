package com.FoodService.service;

import com.FoodService.dto.DishesDTO;
import java.util.List;

public interface DishesService {
    DishesDTO createDish(DishesDTO dishesDTO);
    List<DishesDTO> getAllDishes();
    DishesDTO getDishById(Long id);
    DishesDTO updateDish(Long id, DishesDTO dishesDTO);
    void deleteDish(Long id);
}