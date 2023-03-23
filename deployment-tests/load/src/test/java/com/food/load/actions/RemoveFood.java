package com.food.load.actions;

import com.food.load.ConfigHelper;
import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RemoveFood {

    public static final ChainBuilder chain = exec(http("Remove Food")
        .delete("/foods/" + ConfigHelper.PRODUCT_ID)
        .check(status().is(200)))
        .pause(2);

}
