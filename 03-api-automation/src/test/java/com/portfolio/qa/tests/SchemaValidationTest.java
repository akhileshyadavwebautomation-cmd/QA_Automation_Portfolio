package com.portfolio.qa.tests;

import com.portfolio.qa.api.ApiConfig;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class SchemaValidationTest {
    @Test
    public void postResponseMatchesJsonSchema() {
        given().baseUri(ApiConfig.BASE_URL)
            .when().get("/posts/1")
            .then().statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }
}
