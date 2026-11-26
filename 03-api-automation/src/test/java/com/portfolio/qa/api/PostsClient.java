
package com.portfolio.qa.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostsClient {

    private static final String POSTS_ENDPOINT = "/posts";

    public Response getPost(int postId) {

        return given()
                .baseUri(ApiConfig.BASE_URL)
                .accept(JSON)
        .when()
                .get(POSTS_ENDPOINT + "/" + postId);
    }

    public Response getPostsByUser(int userId) {

        return given()
                .baseUri(ApiConfig.BASE_URL)
                .accept(JSON)
                .queryParam("userId", userId)
        .when()
                .get(POSTS_ENDPOINT);
    }

    public Response createPost(String title, String body, int userId) {

        return given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType(JSON)
                .accept(JSON)
                .body("""
                    {
                        "title": "%s",
                        "body": "%s",
                        "userId": %d
                    }
                    """.formatted(title, body, userId))
        .when()
                .post(POSTS_ENDPOINT);
    }

    public Response updatePost(
            int postId,
            String title,
            String body,
            int userId) {

        return given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType(JSON)
                .accept(JSON)
                .body("""
                    {
                        "title": "%s",
                        "body": "%s",
                        "userId": %d
                    }
                    """.formatted(title, body, userId))
        .when()
                .put(POSTS_ENDPOINT + "/" + postId);
    }

    public Response deletePost(int postId) {

        return given()
                .baseUri(ApiConfig.BASE_URL)
        .when()
                .delete(POSTS_ENDPOINT + "/" + postId);
    }
}
