package org.example.teamcitytesting.ui.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.teamcitytesting.ui.BaseUiTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BuildPageCommon extends BaseUiTest {

    public SelenideElement node = $("#runnerId");
    public SelenideElement useSelectedButton = $("#discoveredRunners > div > a.btn.btn_primary");
    public SelenideElement addBuildStep = $("#buildStepsContainerInner > div > a:nth-child(1) > span");
    public SelenideElement searchType = $("[id^='ring-input-0-']");
    public SelenideElement scriptBox = $(".CodeMirror-code");
    public SelenideElement stepSubmitButton = $("#saveButtons > input.btn.btn_primary.submitButton");

    public void createBuild(String runnerType, String command) {
        node.shouldBe(Condition.enabled, Duration.ofSeconds(10)).click();
        useSelectedButton.click();
        addBuildStep.click();
        searchType.val(runnerType).pressEnter();
        executeJavaScript("arguments[0].CodeMirror.setValue(\"echo 'Hello world!'\");", $(".CodeMirror"));

        stepSubmitButton.click();

    }
}
