package com.food.smoke;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestUtils {

    private static final ClassLoader CLASS_LOADER = TestUtils.class.getClassLoader();

    public static File getRequestBodyFile(String filename) {
        URL fileUrl = CLASS_LOADER.getResource(filename);
        return new File(fileUrl.getFile());
    }

    public static String getRequestBodyFileAsString(String filename) {
        try {
            return FileUtils.readFileToString(getRequestBodyFile(filename), Charset.defaultCharset());
        } catch (IOException e) {
            String errorMessage = String.format("File \"%s\" not found", filename);
            throw new UncheckedIOException(errorMessage, e);
        }
    }

    public static Map<String, Object> getRequestBodyJsonAsMap(String jsonString) {
        try {
            return new ObjectMapper().readValue(jsonString, HashMap.class);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot deserialize json", e);
        }
    }

}
