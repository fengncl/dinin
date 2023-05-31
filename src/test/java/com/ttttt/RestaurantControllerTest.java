package com.ttttt;

import com.ttttt.core.db.entity.Canteen;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class RestaurantControllerTest {
    @Test
    @DisplayName("Get All Canteens Test")
    public void testGetCanteenAll() {
        given()
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/canteen/all")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    @Test
    @DisplayName("Create Canteen Test")
    public void testCreateCanteen() {
        Canteen canteen = new Canteen();
        // 设置canteen对象的属性值
        canteen.setName("Test Canteen");
        canteen.setPhone("1234567890");
        canteen.setPostalCode("12345");

        given()
                .port(80)
                .contentType("application/json")
                .accept("application/json")
                .body(canteen)
                .when()
                .post("/canteen")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

}
