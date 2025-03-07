package com.orders.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.orders.dto.ProductDto;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:7.0.7");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }
    @Test
    void shouldCreateProduct() throws Exception {
        ProductDto productDto = getProductRequest();
        RestAssured.given()
                .contentType("application/json")
                .body(productDto)
                .when()
                .post("/api/products")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo(productDto.getName()))
                .body("description", Matchers.equalTo(productDto.getDescription()))
                .body("price", Matchers.is(productDto.getPrice().intValueExact()));
    }

    private ProductDto getProductRequest() {
        return new ProductDto("test", "test", new BigDecimal(1));
    }
}