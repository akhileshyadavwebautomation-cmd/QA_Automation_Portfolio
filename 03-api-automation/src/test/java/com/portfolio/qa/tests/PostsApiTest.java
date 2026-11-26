
package com.portfolio.qa.tests;

import com.portfolio.qa.api.PostsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostsApiTest {

    private final PostsClient posts = new PostsClient();

    @Test(description = "Verify existing post response contract")
    public void getExistingPostReturnsExpectedContract() {

        Response response = posts.getPost(1);

        response.then()
                .statusCode(200)
                .contentType(containsString("application/json"));

        assertThat(response.jsonPath().getInt("id"), equalTo(1));
        assertThat(response.jsonPath().getInt("userId"), greaterThan(0));
        assertThat(response.jsonPath().getString("title"),
                not(isEmptyOrNullString()));
        assertThat(response.jsonPath().getString("body"),
                not(isEmptyOrNullString()));
    }

    @Test(description = "Verify posts can be filtered by user ID")
    public void filterPostsByUserReturnsMatchingRecords() {

        Response response = posts.getPostsByUser(1);

        response.then().statusCode(200);

        assertThat(response.jsonPath().getList("userId", Integer.class),
                is(not(empty())));

        assertThat(response.jsonPath().getList("userId", Integer.class),
                everyItem(equalTo(1)));
    }

    @Test(description = "Verify new post creation")
    public void createPostReturnsCreatedRepresentation() {

        String expectedTitle = "Portfolio API test";
        String expectedBody = "Created by REST Assured";
        int expectedUserId = 7;

        Response response = posts.createPost(
                expectedTitle,
                expectedBody,
                expectedUserId
        );

        response.then()
                .statusCode(201)
                .contentType(containsString("application/json"));

        assertThat(response.jsonPath().getInt("id"), greaterThan(0));
        assertThat(response.jsonPath().getString("title"),
                equalTo(expectedTitle));
        assertThat(response.jsonPath().getString("body"),
                equalTo(expectedBody));
        assertThat(response.jsonPath().getInt("userId"),
                equalTo(expectedUserId));
    }

    @Test(description = "Verify existing post update")
    public void updatePostReturnsUpdatedRepresentation() {

        String expectedTitle = "Updated title";
        String expectedBody = "Updated body";
        int expectedUserId = 1;

        Response response = posts.updatePost(
                1,
                expectedTitle,
                expectedBody,
                expectedUserId
        );

        response.then()
                .statusCode(200)
                .contentType(containsString("application/json"));

        assertThat(response.jsonPath().getInt("id"), equalTo(1));
        assertThat(response.jsonPath().getString("title"),
                equalTo(expectedTitle));
        assertThat(response.jsonPath().getString("body"),
                equalTo(expectedBody));
        assertThat(response.jsonPath().getInt("userId"),
                equalTo(expectedUserId));
    }

    @Test(description = "Verify post deletion response")
    public void deletePostReturnsSuccessStatus() {

        Response response = posts.deletePost(1);

        response.then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test(description = "Verify unknown post returns 404")
    public void unknownPostReturnsNotFound() {

        Response response = posts.getPost(999999);

        response.then().statusCode(404);
    }
}
