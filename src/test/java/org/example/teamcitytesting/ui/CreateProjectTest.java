package org.example.teamcitytesting.ui;

import com.codeborne.selenide.SelenideElement;
import org.example.teamcitytesting.ui.common.ProjectCreation;
import org.example.teamcitytesting.ui.pages.ProjectsPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest {

    private static final String REPO_URL = "https://github.com/kimbyb/gallery.git";

    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProject() {
        // preparation
        loginAs(testData.getUser());

        ProjectCreation projectCreation = new ProjectCreation();
        projectCreation.createProjectWithChecks(REPO_URL,testData.getProject().getName(), testData.getBuildType().getName());

        //softy.assertTrue(projectCreation.checkProjectExists(testData.getProject().getName()));
       //var projectExists = ProjectsPage.open().getProjects().stream().anyMatch(project -> project.getName().getText().equals(testData.getProject().getName()));
        softy.assertTrue(projectCreation.checkProjectExists(testData.getProject().getName()));
    }

    @Test(description = "User should not be able to create project without name", groups = {"Negative"})
    public void userCreatesProjectWithoutName() {
        // preparation
        loginAs(testData.getUser());

        var numberOfProjectsBefore = ProjectsPage.open().getProjects().size();

        SelenideElement errorMessage = $("#error_projectName");

        ProjectCreation projectCreation = new ProjectCreation();
        projectCreation.createProjectNoChecks(REPO_URL,"", testData.getBuildType().getName());


        softy.assertTrue(errorMessage.isDisplayed());

        var numberOfProjectsAfter = ProjectsPage.open().getProjects().size();
        softy.assertEquals(numberOfProjectsAfter, numberOfProjectsBefore);

        //UI check
        step("Check that error appears Project name must not be empty");
    }
}
