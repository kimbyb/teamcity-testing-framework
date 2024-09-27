package org.example.teamcitytesting.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.teamcitytesting.api.models.BaseModel;
import org.example.teamcitytesting.api.models.BuildType;


@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
