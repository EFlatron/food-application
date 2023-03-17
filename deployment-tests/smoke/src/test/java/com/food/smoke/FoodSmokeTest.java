package com.food.smoke;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import io.restassured.http.ContentType;

import static com.food.smoke.TestData.FOOD_ID;
import static com.food.smoke.TestData.getFoodRequest;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class FoodSmokeTest {

    @Test
    void step00_POST_createFood() {
        given().
            when()
            .contentType(ContentType.JSON)
            .body(getFoodRequest())
            .post("/foods").
            then()
            .statusCode(200);
    }

    @Test
    void step01_GET_getAllFood() {
        given().
            when()
            .contentType(ContentType.JSON)
            .get("/foods").
            then()
            .statusCode(200);
    }

    @Test
    void step02_getFoodById() {
        given().
            when()
            .contentType(ContentType.JSON)
            .pathParam("id", FOOD_ID)
            .get("/foods/{id}").
            then()
            .statusCode(200);
    }

    @Test
    void step99_removeFoodById() {
        given().
            when()
            .contentType(ContentType.JSON)
            .pathParam("id", FOOD_ID)
            .delete("/foods/{id}").
            then()
            .statusCode(200);
    }

}
