package org.example.teamcitytesting.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.teamcitytesting.api.models.*;


@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    PROJECT("/app/rest/projects", Project.class),
    USERS("/app/rest/users", User.class),
    ROLES("/app/rest/roles,",Role.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
