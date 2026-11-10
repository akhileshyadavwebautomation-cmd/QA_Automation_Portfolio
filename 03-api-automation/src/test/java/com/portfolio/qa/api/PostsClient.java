package com.portfolio.qa.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostsClient {
    public Response getPost(int id) {
        return given().baseUri(ApiConfig.BASE_URL)
            .accept("application/json")
            .when().get("/posts/{id}", id);
    }

    public Response getPostsByUser(int userId) {
        return given().baseUri(ApiConfig.BASE_URL)
            .queryParam("userId", userId)
            .accept("application/json")
            .when().get("/posts");
    }

    public Response createPost(String title, String body, int userId) {
        return given().baseUri(ApiConfig.BASE_URL)
            .contentType("application/json")
            .body(String.format("{\"title\":\"%s\",\"body\":\"%s\",\"userId\":%d}", title, body, userId))
            .when().post("/posts");
    }

    public Response updatePost(int id, String title, String body, int userId) {
        return given().baseUri(ApiConfig.BASE_URL)
            .contentType("application/json")
            .body(String.format("{\"id\":%d,\"title\":\"%s\",\"body\":\"%s\",\"userId\":%d}", id, title, body, userId))
            .when().put("/posts/{id}", id);
    }

    public Response deletePost(int id) {
        return given().baseUri(ApiConfig.BASE_URL).when().delete("/posts/{id}", id);
    }
}
