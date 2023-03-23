package com.food.load.actions;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GetAllFoods {

    public static final ChainBuilder chain = exec(http("Get All Foods")
        .get("/foods")
        .check(status().is(200)))
        .pause(10);

}
