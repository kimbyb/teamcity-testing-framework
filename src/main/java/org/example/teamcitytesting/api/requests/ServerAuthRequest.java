package org.example.teamcitytesting.api.requests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.teamcitytesting.api.models.ServerAuthSettings;

public class ServerAuthRequest {
    private static final String SERVER_AUTH_SETTINGS_URL = "/app/rest/server/authSettings";

    public ServerAuthRequest(RequestSpecification spec) {
        this.spec = spec;
    }

    private RequestSpecification spec;

    public ServerAuthSettings read() {
        return RestAssured.given()
                .spec(spec)
                .get(SERVER_AUTH_SETTINGS_URL)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(ServerAuthSettings.class);
    }

    public ServerAuthSettings update(ServerAuthSettings authSettings) {
         return RestAssured.given()
                 .spec(spec)
                 .body(authSettings)
                 .put(SERVER_AUTH_SETTINGS_URL)
                 .then().assertThat().statusCode(HttpStatus.SC_OK)
                 .extract().as(ServerAuthSettings.class);
    }
}
