package com.FoodService.controller;

import com.FoodService.dto.DishesDTO;
import com.FoodService.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishesController {

    @Autowired
    private DishesService dishesService;

    @PostMapping("/create")
    public ResponseEntity<DishesDTO> createDish(@RequestBody DishesDTO dishesDTO) {
        return ResponseEntity.ok(dishesService.createDish(dishesDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<DishesDTO>> getAllDishes() {
        return ResponseEntity.ok(dishesService.getAllDishes());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<DishesDTO> getDishById(@RequestParam Long id) {
        return ResponseEntity.ok(dishesService.getDishById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<DishesDTO> updateDish(@RequestParam Long id, @RequestBody DishesDTO dishesDTO) {
        return ResponseEntity.ok(dishesService.updateDish(id, dishesDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDish(@RequestParam Long id) {
        dishesService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}