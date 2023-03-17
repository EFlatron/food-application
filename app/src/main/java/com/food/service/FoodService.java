package com.food.service;

import com.food.entity.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface FoodService {

    List<Food> getAllFoods();

    UUID addFood(Food food);

    Food getFoodById(UUID id);

    void removeFood(UUID id);

}
