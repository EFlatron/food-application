package com.food.load.actions;

import com.food.load.ConfigHelper;
import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GetFoodById {

    public static final ChainBuilder chain = exec(http("Get Food by ID")
        .get("/foods/" + ConfigHelper.PRODUCT_ID)
        .check(status().is(200)))
        .pause(2);

}
