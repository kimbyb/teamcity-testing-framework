package org.example.teamcitytesting.api.requests;

import io.restassured.specification.RequestSpecification;
import org.example.teamcitytesting.api.models.BaseModel;
import org.example.teamcitytesting.api.requests.checked.CheckedBase;
import org.example.teamcitytesting.enums.Endpoint;

import java.util.EnumMap;

public class CheckedRequests {
    private final EnumMap<Endpoint, CheckedBase> requests = new EnumMap<>(Endpoint.class);

    public CheckedRequests(RequestSpecification spec) {
        for (var endpoint: Endpoint.values()) {
            requests.put(endpoint, new CheckedBase(spec, endpoint));
        }
    }

    public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
        return (CheckedBase<T>)  requests.get(endpoint);
    }
}
