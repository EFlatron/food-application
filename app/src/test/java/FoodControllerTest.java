import com.food.controller.FoodController;
import com.food.entity.Food;
import com.food.entity.CustomException;
import com.food.service.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class FoodControllerTest {

    @Mock
    private FoodService foodService;

    @InjectMocks
    private FoodController foodController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFoods() {
        List<Food> expectedFoods = new ArrayList<>();
        expectedFoods.add(new Food(UUID.randomUUID(), "Pizza", "4 cheese"));
        expectedFoods.add(new Food(UUID.randomUUID(), "Burger", "Cheeseburger"));

        when(foodService.getAllFoods()).thenReturn(expectedFoods);

        List<Food> actualFoods = foodController.getAllFoods();

        assertEquals(expectedFoods, actualFoods);
    }

    @Test
    public void testAddFood() {
        UUID expectedId = UUID.randomUUID();
        Food foodToSave = new Food(null, "Pizza", "4 cheese");

        when(foodService.addFood(foodToSave)).thenReturn(expectedId);

        ResponseEntity<UUID> responseEntity = foodController.addFood(foodToSave);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedId, responseEntity.getBody());
    }

    @Test
    public void testGetFoodById() {
        UUID id = UUID.randomUUID();
        Food expectedFood = new Food(id, "Pizza", "4 cheese");

        when(foodService.getFoodById(id)).thenReturn(expectedFood);

        Food actualFood = foodController.getFoodById(id);

        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodByIdNotFoundException() {
        UUID id = UUID.randomUUID();

        when(foodService.getFoodById(id)).thenThrow(new CustomException("Food not found"));

        assertThrows(CustomException.class, () -> foodController.getFoodById(id));
    }

    @Test
    public void testRemoveFood() {
        UUID id = UUID.randomUUID();

        ResponseEntity<String> responseEntity = foodController.removeFood(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Food with ID " + id + " has been deleted successfully", responseEntity.getBody());
    }

    @Test
    public void testRemoveFoodNotFoundException() {
        UUID id = UUID.randomUUID();

        doThrow(new CustomException("Food not found")).when(foodService).removeFood(id);

        assertThrows(CustomException.class, () -> foodController.removeFood(id));
    }

}
