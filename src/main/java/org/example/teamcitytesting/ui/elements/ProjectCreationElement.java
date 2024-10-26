package org.example.teamcitytesting.ui.elements;

import com.codeborne.selenide.SelenideElement;

public class ProjectCreationElement extends BasePageElement{

    private SelenideElement errorMessage;

    public ProjectCreationElement(SelenideElement element) {
        super(element);
        this.errorMessage = find("#error_projectName");
    }


}
