package org.example.teamcitytesting.ui.common;

import com.codeborne.selenide.SelenideElement;
import org.example.teamcitytesting.ui.BaseUiTest;


import static com.codeborne.selenide.Selenide.$;

public class ProjectPageCommon extends BaseUiTest {
    public SelenideElement search = $("#search-projects");

    public static SelenideElement search(String projectId) {
        SelenideElement search = $("#search-projects");
        return search.val(projectId);
    }
}
