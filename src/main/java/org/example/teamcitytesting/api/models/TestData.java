package org.example.teamcitytesting.api.models;

import lombok.Data;

import java.util.List;

@Data
public class TestData {
    private Project project;
    private User user;
    private BuildType buildType;
}
