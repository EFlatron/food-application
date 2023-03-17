package com.food.load;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;

@Slf4j
public class ConfigHelper {

    public static final String ARG_BASE_URL = "baseUrl";
    public static final String ARG_MAX_USERS = "maxUsers";
    public static final String ARG_RAMP_SECS = "rampSeconds";
    public static final String ARG_SUSTAINED_LOAD_SECS = "sustainedLoadSeconds";

    public static final String baseUrl = getProperty(ARG_BASE_URL, "http://localhost:8081");
    public static final int maxUsers = parseInt(getProperty(ARG_MAX_USERS, "5"));
    public static final int rampSeconds = parseInt(getProperty(ARG_RAMP_SECS, "30"));
    public static final int sustainedLoadSeconds = parseInt(getProperty(ARG_SUSTAINED_LOAD_SECS, "60"));

    public static final String PRODUCT_ID = getProperty("productId");

    static {
        log.info("Hitting: {}", baseUrl);
        log.info("Max Users: {}", maxUsers);
        log.info("Ramp Up Seconds: {}", rampSeconds);
        log.info("Sustained Load Seconds: {}", sustainedLoadSeconds);
    }
}
