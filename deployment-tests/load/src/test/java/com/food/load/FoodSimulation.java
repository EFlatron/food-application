package com.food.load;

import com.food.load.actions.AddFood;
import com.food.load.actions.GetAllFoods;
import com.food.load.actions.GetFoodById;
import com.food.load.actions.RemoveFood;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.nothingFor;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class FoodSimulation extends Simulation {

    public FoodSimulation() {

        int setupTime = 10;
        int testDurationSeconds = 2 * ConfigHelper.rampSeconds + ConfigHelper.sustainedLoadSeconds + setupTime;

        HttpProtocolBuilder httpProtocol = http
            .baseUrl(ConfigHelper.baseUrl);

        ScenarioBuilder addFood_scn = scenario("Add Food")
            .during(ConfigHelper.sustainedLoadSeconds).on(
                exec(
                    AddFood.chain
                ));
        ScenarioBuilder getAllFoods_scn = scenario("Get All Foods")
            .during(ConfigHelper.sustainedLoadSeconds).on(
                exec(
                    GetAllFoods.chain
                ));
        ScenarioBuilder getFoodById_scn = scenario("Get Food by ID")
            .during(ConfigHelper.sustainedLoadSeconds).on(
                exec(
                    GetFoodById.chain
                ));
        ScenarioBuilder removeFood = scenario("Remove Food")
            .during(ConfigHelper.sustainedLoadSeconds).on(
                exec(
                    RemoveFood.chain
                ));
        setUp(
            addFood_scn.injectOpen(
                rampUsers(ConfigHelper.maxUsers).during(ConfigHelper.rampSeconds),
                nothingFor(ConfigHelper.sustainedLoadSeconds)
            ),
            getAllFoods_scn.injectOpen(
                rampUsers(ConfigHelper.maxUsers).during(ConfigHelper.rampSeconds),
                nothingFor(ConfigHelper.sustainedLoadSeconds)
            ),
            getFoodById_scn.injectOpen(
                rampUsers(ConfigHelper.maxUsers).during(ConfigHelper.rampSeconds),
                nothingFor(ConfigHelper.sustainedLoadSeconds)
            ),
            removeFood.injectOpen(
                rampUsers(ConfigHelper.maxUsers).during(ConfigHelper.rampSeconds),
                nothingFor(ConfigHelper.sustainedLoadSeconds)
            )
        ).maxDuration(testDurationSeconds)
            .protocols(httpProtocol);
    }

}
