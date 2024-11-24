package org.example.teamcitytesting.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.example.teamcitytesting.BaseTest;
import org.example.teamcitytesting.api.config.Config;
import org.example.teamcitytesting.api.models.User;
import org.example.teamcitytesting.enums.Endpoint;
import org.example.teamcitytesting.ui.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public class BaseUiTest extends BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setupUiTest() {
        Configuration.browser = Config.getProperty("browser");
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.browserSize = Config.getProperty("browserSize");
        //Configuration.headless = true;


        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of("enableVNC", true,
                "enableLog", true
                ));

    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }

    protected void loginAs(User user) {
        superUserCheckRequest.getRequest(Endpoint.USERS).create(testData.getUser());
        LoginPage.open().login(testData.getUser());
    }
}