package org.example.teamcitytesting.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.teamcitytesting.api.models.BaseModel;
import org.example.teamcitytesting.api.models.BuildType;
import org.example.teamcitytesting.api.models.Project;
import org.example.teamcitytesting.api.models.User;


@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    PROJECT("/app/rest/projects", Project.class),
    USERS("/app/rest/users", User.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
