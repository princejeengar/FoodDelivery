package com.FoodService.service.serviceImpl;

import com.FoodService.dto.DishesDTO;
import com.FoodService.dto.RestaurantDTO;
import com.FoodService.entity.Dishes;
import com.FoodService.entity.Restaurant;
import com.FoodService.repository.DishesRepo;
import com.FoodService.repository.RestaurantRepo;
import com.FoodService.service.DishesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishesServiceImpl implements DishesService {

    @Autowired
    private DishesRepo dishesRepository;

    @Autowired
    private RestaurantRepo restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DishesDTO createDish(DishesDTO dishesDTO) {
        Restaurant restaurant = restaurantRepository.findById(dishesDTO.getRestaurant().getRestaurantID())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Dishes dish = modelMapper.map(dishesDTO, Dishes.class);
        dish.setRestaurant(restaurant);
        Dishes savedDish = dishesRepository.save(dish);
        DishesDTO savedDishDTO = modelMapper.map(savedDish, DishesDTO.class);
        savedDishDTO.setRestaurant(modelMapper.map(restaurant, RestaurantDTO.class));

        return savedDishDTO;
    }

    @Override
    public List<DishesDTO> getAllDishes() {
        List<Dishes> dishesList = dishesRepository.findAll();
        return dishesList.stream().map(dish -> {
            DishesDTO dishDTO = modelMapper.map(dish, DishesDTO.class);
            dishDTO.setRestaurant(modelMapper.map(dish.getRestaurant(), RestaurantDTO.class));
            return dishDTO;
        }).toList();
    }

    @Override
    public DishesDTO getDishById(Long id) {
        Dishes dish = dishesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        DishesDTO dishDTO = modelMapper.map(dish, DishesDTO.class);
        dishDTO.setRestaurant(modelMapper.map(dish.getRestaurant(), RestaurantDTO.class));
        return dishDTO;
    }

    @Override
    public DishesDTO updateDish(Long id, DishesDTO dishesDTO) {
        // Fetch dish and restaurant
        Dishes dish = dishesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        Restaurant restaurant = restaurantRepository.findById(dishesDTO.getRestaurant().getRestaurantID())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        dish.setDishName(dishesDTO.getDishName());
        dish.setRestaurant(restaurant);
        Dishes updatedDish = dishesRepository.save(dish);
        DishesDTO updatedDishDTO = modelMapper.map(updatedDish, DishesDTO.class);
        updatedDishDTO.setRestaurant(modelMapper.map(restaurant, RestaurantDTO.class));
        return updatedDishDTO;
    }

    @Override
    public void deleteDish(Long id) {
        dishesRepository.deleteById(id);
    }
}