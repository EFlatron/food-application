package com.food.smoke;

import java.util.Map;
import java.util.UUID;

public class TestData {

    public static UUID FOOD_ID = UUID.fromString("95e66616-c0dd-415a-9c25-369ceb1b7a1c");

    public static Map<String, Object> getFoodRequest() {
        String productJson = TestUtils.getRequestBodyFileAsString("data/food.json");
        return TestUtils.getRequestBodyJsonAsMap(productJson);
    }
}
