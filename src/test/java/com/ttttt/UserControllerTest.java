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
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/users/{id}", 6)
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
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
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

}
