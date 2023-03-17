package com.food.controller;

import com.food.entity.Food;
import com.food.entity.CustomException;
import com.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/foods")
@Slf4j
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        log.info("Requested all foods");
        return foodService.getAllFoods();
    }

    @PostMapping
    public ResponseEntity<UUID> addFood(@RequestBody Food food) {
        log.info("Requested create food {}", food.toString());
        UUID id = foodService.addFood(food);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable("id") UUID id) {
        log.info("requested get food by id {}", id);
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFood(@PathVariable("id") UUID id) {
        log.info("Requested delete food with id {}", id);
        foodService.removeFood(id);
        return ResponseEntity.ok("Food with ID " + id + " has been deleted successfully");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleNotFoundException(CustomException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
