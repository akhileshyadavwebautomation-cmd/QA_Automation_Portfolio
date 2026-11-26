
package com.portfolio.qa.tests;

import com.portfolio.qa.api.ApiConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest {

    @Test(description = "Verify GET post response matches the JSON schema")
    public void postResponseMatchesJsonSchema() {

        Response response =
                given()
                    .baseUri(ApiConfig.BASE_URL)
                    .accept(JSON)
                .when()
                    .get("/posts/1");

        response.then()
                .statusCode(200)
                .contentType(JSON)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/post-schema.json"
                ));
    }

    @Test(description = "Verify JSON schema for another existing post")
    public void anotherPostResponseMatchesJsonSchema() {

        given()
            .baseUri(ApiConfig.BASE_URL)
            .accept(JSON)
        .when()
            .get("/posts/2")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body(matchesJsonSchemaInClasspath(
                    "schemas/post-schema.json"
            ));
    }
}
