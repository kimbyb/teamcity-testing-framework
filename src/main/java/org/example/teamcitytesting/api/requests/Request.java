package org.example.teamcitytesting.api.requests;

import io.restassured.specification.RequestSpecification;
import org.example.teamcitytesting.enums.Endpoint;

public class Request {
    private final RequestSpecification spec;
    private final Endpoint endpoint;

    public Request(RequestSpecification spec, Endpoint endpoint) {
        this.spec = spec;
        this.endpoint = endpoint;
    }
}
