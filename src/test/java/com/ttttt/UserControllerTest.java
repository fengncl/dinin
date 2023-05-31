package com.ttttt;

import com.ttttt.core.db.entity.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class UserControllerTest {

    @Test
    @DisplayName("Get User By ID Test")
    public void testGetUserById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/{id}", 1)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(1));
    }

    @Test
    @DisplayName("Create User Test")
    public void testCreateUser() {
        User user = new User();
        // 设置user对象的属性值
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(200);
    }

}
