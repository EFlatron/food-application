import com.food.entity.Food;
import com.food.entity.CustomException;
import com.food.service.FoodService;
import com.food.service.impl.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodServiceImplTest {

    private FoodService foodService;

    @BeforeEach
    public void setUp() {
        foodService = new FoodServiceImpl();
    }

    @Test
    public void testAddFood() {
        Food food = new Food(UUID.randomUUID(), "Pizza", "4 Cheese");

        UUID foodId = foodService.addFood(food);

        assertNotNull(foodId);
        assertEquals(food.getId(), foodId);
        assertTrue(foodService.getAllFoods().contains(food));
    }

    @Test
    void testAddFoodWithNullId() {
        Food food = new Food(null, "Pizza", "4 Cheese");

        assertThrows(CustomException.class, () -> foodService.addFood(food));

        assertTrue(foodService.getAllFoods().isEmpty());
    }

    @Test
    public void testAddFoodWithExistingIdShouldThrowCustomException() {
        UUID id = UUID.randomUUID();
        Food food1 = new Food(id, "Pizza", "4 Cheese");
        foodService.addFood(food1);

        Food food2 = new Food(id, "Burger", "Cheeseburger");

        assertThrows(CustomException.class, () -> foodService.addFood(food2));

        assertEquals(1, foodService.getAllFoods().size());
    }

    @Test
    public void testGetAllFoods() {
        Food food1 = new Food(UUID.randomUUID(), "Pizza", "4 Cheese");
        foodService.addFood(food1);

        Food food2 = new Food(UUID.randomUUID(), "Burger", "Cheeseburger");
        foodService.addFood(food2);

        List<Food> foodList = foodService.getAllFoods();

        assertEquals(2, foodList.size());
        assertTrue(foodList.contains(food1));
        assertTrue(foodList.contains(food2));
    }

    @Test
    public void testGetFoodById() {
        Food expectedFood = new Food(UUID.randomUUID(), "Pizza", "4 Cheese");
        UUID expectedFoodId = foodService.addFood(expectedFood);
        Food actualFood = foodService.getFoodById(expectedFoodId);

        assertEquals(expectedFood, actualFood);

        assertThrows(CustomException.class, () -> {
            UUID nonExistentId = UUID.randomUUID();
            foodService.getFoodById(nonExistentId);
        });
    }

    @Test
    public void testRemoveExistingFood() {
        UUID id = UUID.randomUUID();
        Food food = new Food(id, "Pizza", "4 Cheese");
        foodService.addFood(food);

        foodService.removeFood(id);

        assertTrue(foodService.getAllFoods().isEmpty());
    }

    @Test
    public void testRemoveFoodWithNonExistentIdShouldThrowCustomException() {
        UUID id = UUID.randomUUID();

        assertThrows(CustomException.class, () -> foodService.removeFood(id));
    }
}
