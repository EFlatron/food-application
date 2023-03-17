package com.food.service.impl;

import com.food.entity.Food;
import com.food.entity.CustomException;
import com.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final List<Food> foodList = new ArrayList<>();

    @Override
    public List<Food> getAllFoods() {
        return foodList;
    }

    @Override
    public UUID addFood(Food food) {
        UUID id = food.getId();
        if (id == null) {
            throw new CustomException("Food id cannot be null");
        }
        if (foodList.stream().anyMatch(existingFood -> existingFood.getId().equals(id))) {
            throw new CustomException("Food with ID " + id + " already exists");
        }
        food.setId(food.getId());
        foodList.add(food);
        return id;
    }

    public Food getFoodById(UUID id) {
        return foodList.stream()
            .filter(food -> food.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new CustomException("Food not found with ID: " + id));
    }

    @Override
    public void removeFood(UUID id) {
        boolean foodRemoved = foodList.removeIf(food -> food.getId().equals(id));
        if (!foodRemoved) {
            throw new CustomException("Food not found with ID: " + id);
        }
    }
}
