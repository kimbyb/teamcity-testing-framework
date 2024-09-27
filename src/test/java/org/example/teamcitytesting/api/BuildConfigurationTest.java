package org.example.teamcitytesting.api;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.example.teamcitytesting.api.models.User;
import org.example.teamcitytesting.api.spec.Specifications;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {

    @Test
    public void buildConfigurationTest() {
        var user = User.builder()
                .username("admin")
                .password("password")
                .build();

        var token = RestAssured
                .given()
                .spec(Specifications.getSpec().authSpec(user))
                .get("/authenticationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();

        System.out.println(token);


        //GET http://admin:password@192.168.2.154:8111/authenticationTest.html?csrf
        //Accept: application/json
    }
}
