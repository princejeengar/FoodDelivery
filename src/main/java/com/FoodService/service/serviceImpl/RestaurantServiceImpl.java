package com.FoodService.service.serviceImpl;

import com.FoodService.dto.RestaurantDTO;
import com.FoodService.entity.Restaurant;
import com.FoodService.repository.RestaurantRepo;
import com.FoodService.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepo.findAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantRepo.findById(id)
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class)).orElse(null);
    }

    @Override
    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepo.findById(id).orElse(null);
        if(existingRestaurant != null){
            existingRestaurant.setRestaurantName(restaurantDTO.getRestaurantName());
            existingRestaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
            return modelMapper.map(restaurantRepo.save(existingRestaurant), RestaurantDTO.class);
        }
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }
}
