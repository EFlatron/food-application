package com.food.load.actions;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AddFood {

    public static final ChainBuilder chain = exec(http("Add Food")
        .post("/foods")
        .body(ElFileBody("bodies/add_food.json")).asJson()
        .check(status().is(200)))
        .pause(2);

}
