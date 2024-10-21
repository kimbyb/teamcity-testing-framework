package org.example.teamcitytesting.ui;

import org.testng.annotations.Test;
import static io.qameta.allure.Allure.step;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest{

    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProject() {
        // preparation
        step("Login as user");

        //ui interations
        step("Open Create project page ");
        step("Send all project parameters (repo url");
        step("Click proceed");
        step("Fix project name and build type values");
        step("Click proceed");

        // api checks
        step("Check that all entities (project, build type) were successfully created with correct data on API lvl");

        //ui check
        step("Check that project is visible on Projects page ");
    }

    @Test(description = "User should not be able to create project without name", groups = {"Negative"})
    public void userCreatesProjectWithoutName() {
        // preparation
        step("Login as user");
        step("Check number of projects")l

        //ui interations
        step("Open Create project page ");
        step("Send all project parameters (repo url)");
        step("Click proceed");
        step("Set project name to empty");
        step("Click proceed");

        // api checks
        step("Check that number of projects did not change");

        //UI check
        step("Check that error appears Project name must not be empty");
    }
}
