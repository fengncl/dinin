package com.ttttt;

import com.ttttt.core.db.entity.Comment;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CommentControllerTest {

    @Test
    @DisplayName("Create Comment Test")
    public void testCreateComment() {
        Comment comment = new Comment();
        // 设置comment对象的属性值
        comment.setcId(1);
        comment.setuId(6);
        comment.setContent("This is a comment");
        comment.setGrade(4.5);

        given()
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .body(comment)
                .when()
                .post("/comment/")
                .then()
                .statusCode(200)
                .body("msg", equalTo("Request successful"));
    }

    @Test
    @DisplayName("Get Comments Test")
    public void testGetComments() {
        Integer userId = 6;

        given()
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .queryParam("uid", userId)
                .when()
                .get("/comment/all")
                .then()
                .statusCode(200)
                .body("msg", equalTo("Request successful"));
    }



}
