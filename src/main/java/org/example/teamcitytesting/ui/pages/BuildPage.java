package org.example.teamcitytesting.ui.pages;

import com.codeborne.selenide.Selenide;

public class BuildPage {

    private static final String PROJECTS_URL = "/buildConfiguration/";

    public static BuildPage open(String buildId) {
        return Selenide.open("/buildConfiguration/" + buildId, BuildPage.class);
    }

}
