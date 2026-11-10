package com.portfolio.qa.tests;

import com.portfolio.qa.api.PostsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostsApiTest {
    private final PostsClient posts = new PostsClient();

    @Test
    public void getExistingPostReturnsExpectedContract() {
        Response response = posts.getPost(1);
        response.then().statusCode(200).contentType(containsString("application/json"));
        assertThat(response.jsonPath().getInt("id"), equalTo(1));
        assertThat(response.jsonPath().getInt("userId"), greaterThan(0));
        assertThat(response.jsonPath().getString("title"), not(isEmptyOrNullString()));
    }

    @Test
    public void filterPostsByUserReturnsMatchingRecords() {
        Response response = posts.getPostsByUser(1);
        response.then().statusCode(200);
        assertThat(response.jsonPath().getList("userId", Integer.class), everyItem(equalTo(1)));
    }

    @Test
    public void createPostReturnsCreatedRepresentation() {
        Response response = posts.createPost("Portfolio API test", "Created by REST Assured", 7);
        response.then().statusCode(201);
        assertThat(response.jsonPath().getString("title"), equalTo("Portfolio API test"));
        assertThat(response.jsonPath().getInt("userId"), equalTo(7));
    }

    @Test
    public void updatePostReturnsUpdatedRepresentation() {
        Response response = posts.updatePost(1, "Updated title", "Updated body", 1);
        response.then().statusCode(200);
        assertThat(response.jsonPath().getString("title"), equalTo("Updated title"));
    }

    @Test
    public void deletePostReturnsSuccessStatus() {
        posts.deletePost(1).then().statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void unknownPostReturnsNotFound() {
        posts.getPost(999999).then().statusCode(404);
    }
}
