package com.FoodService.service.serviceImpl;

import com.FoodService.dto.AppUserDTO;
import com.FoodService.dto.DishesDTO;
import com.FoodService.dto.OrderDTO;
import com.FoodService.dto.RestaurantDTO;
import com.FoodService.entity.AppUser;
import com.FoodService.entity.Dishes;
import com.FoodService.entity.Order;
import com.FoodService.repository.AppUserRepo;
import com.FoodService.repository.DishesRepo;
import com.FoodService.repository.OrderRepo;
import com.FoodService.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private DishesRepo dishesRepository;

    @Autowired
    private AppUserRepo appUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        AppUser appUser = appUserRepository.findById(orderDTO.getAppUser().getAppUserId())
                .orElseThrow(() -> new RuntimeException("AppUser not found"));
        Dishes dish = dishesRepository.findById(orderDTO.getDishes().getDishID())
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setAppUser(appUser);
        order.setDishes(dish);

        Order savedOrder = orderRepository.save(order);
        OrderDTO savedOrderDTO = modelMapper.map(savedOrder, OrderDTO.class);
        savedOrderDTO.setAppUser(modelMapper.map(appUser, AppUserDTO.class));
        savedOrderDTO.setDishes(modelMapper.map(dish, DishesDTO.class));
        savedOrderDTO.getDishes().setRestaurant(modelMapper.map(dish.getRestaurant(), RestaurantDTO.class));

        return savedOrderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
            orderDTO.setAppUser(modelMapper.map(order.getAppUser(), AppUserDTO.class));
            DishesDTO dishesDTO = modelMapper.map(order.getDishes(), DishesDTO.class);
            dishesDTO.setRestaurant(modelMapper.map(order.getDishes().getRestaurant(), RestaurantDTO.class));
            orderDTO.setDishes(dishesDTO);

            return orderDTO;
        }).toList();
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setAppUser(modelMapper.map(order.getAppUser(), AppUserDTO.class));
        DishesDTO dishesDTO = modelMapper.map(order.getDishes(), DishesDTO.class);
        dishesDTO.setRestaurant(modelMapper.map(order.getDishes().getRestaurant(), RestaurantDTO.class));
        orderDTO.setDishes(dishesDTO);
        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        AppUser appUser = appUserRepository.findById(orderDTO.getAppUser().getAppUserId())
                .orElseThrow(() -> new RuntimeException("AppUser not found"));
        Dishes dish = dishesRepository.findById(orderDTO.getDishes().getDishID())
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        order.setAppUser(appUser);
        order.setDishes(dish);
        Order updatedOrder = orderRepository.save(order);
        OrderDTO updatedOrderDTO = modelMapper.map(updatedOrder, OrderDTO.class);
        updatedOrderDTO.setAppUser(modelMapper.map(appUser, AppUserDTO.class));
        DishesDTO dishesDTO = modelMapper.map(updatedOrder.getDishes(), DishesDTO.class);
        dishesDTO.setRestaurant(modelMapper.map(updatedOrder.getDishes().getRestaurant(), RestaurantDTO.class));
        updatedOrderDTO.setDishes(dishesDTO);
        return updatedOrderDTO;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}